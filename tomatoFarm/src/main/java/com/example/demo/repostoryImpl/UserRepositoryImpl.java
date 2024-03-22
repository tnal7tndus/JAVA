package com.example.demo.repostoryImpl;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import com.example.demo.domain.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.AllArgsConstructor;

import static com.example.demo.entity.QUser.user;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {
	/* 🎃🎃🎃🎃🎃🎃 검수 전 🎃🎃🎃🎃🎃🎃 */

	
	
	/* 🎃🎃🎃🎃🎃🎃 수미 🎃🎃🎃🎃🎃🎃 */
	private final JPAQueryFactory jpaQueryfactory;
	private final EntityManager entityManager;

	@Override
	public User selectUser(UserDTO dto) {
		return jpaQueryfactory.selectFrom(user)
		.where(user.id.eq(dto.getId()))
		.fetchOne();
	}
	
	
	
	@Override
	@Transactional
	public int insertUser(UserDTO dto) {
		// SQL insert 사용시 EntityManager 사용
		return entityManager
					.createNativeQuery("INSERT INTO user(id,password,username,phonenumber"
											+ ",address2,email,email2,gender,birthdate) "
											+ "VALUE(?,?,?,?,?,?,?,?,?)")
					.setParameter(1, dto.getId())
					.setParameter(2, dto.getPassword())
					.setParameter(3, dto.getUsername())
					.setParameter(4, dto.getPhonenumber())
					.setParameter(5, dto.getAddress2())
					.setParameter(6, dto.getEmail())
					.setParameter(7, dto.getEmail2())
					.setParameter(8, dto.getGender())
					.setParameter(9, dto.getBirthdate())
					.executeUpdate();
	}

	
	
	

}
