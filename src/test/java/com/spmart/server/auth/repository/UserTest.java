package com.spmart.server.auth.repository;

import java.io.FileInputStream;
import java.io.IOException;

import javax.persistence.EntityNotFoundException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.io.ClassPathResource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.spmart.server.auth.domain.User;

@DataJpaTest
class UserTest {
	@Autowired
	UserRepository userRepository;

	@BeforeAll
	static void initTest() throws IOException {
		ClassPathResource resource = new ClassPathResource("adminsdk.json");
		FileInputStream serviceAccount = new FileInputStream(resource.getFile());
		FirebaseOptions options = new FirebaseOptions.Builder()
			.setCredentials(GoogleCredentials.fromStream(serviceAccount))
			.setDatabaseUrl("https://sp-mart.firebaseio.com/")
			.build();
		FirebaseApp.initializeApp(options);
	}

	@Test
	@DisplayName("유저 회원")
	public void createUserTest() throws FirebaseAuthException {
		// firebase 회원가입
		UserRecord.CreateRequest request = new UserRecord.CreateRequest()
			.setEmail("test@test.com")
			.setEmailVerified(false)
			.setPassword("secretPassword")
			.setPhoneNumber("+821012341234")
			.setDisabled(false);
		UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);

		String uId = userRecord.getUid();
		// local 회원가입
		User user = User.builder()
			.id(uId)
			.email(userRecord.getEmail())
			.phone(userRecord.getPhoneNumber())
			.name("nickname")
			.address("서울시 서대문구") // 주소 넣는 api 검색 넣기
			.build();
		userRepository.save(user);

		// 검사
		User findUser = userRepository.findById(userRecord.getUid())
			.orElseThrow(EntityNotFoundException::new);

		Assertions.assertThat(findUser.getId()).isEqualTo(userRecord.getUid());
		Assertions.assertThat(findUser.getEmail()).isEqualTo(userRecord.getEmail());
		Assertions.assertThat(findUser.getPhone()).isEqualTo(userRecord.getPhoneNumber());

		FirebaseAuth.getInstance().deleteUser(uId);
	}

}