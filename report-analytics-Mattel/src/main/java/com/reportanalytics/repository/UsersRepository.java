package com.reportanalytics.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.reportanalytics.entities.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

	@Query(value = "SELECT TOP 1 * FROM users WHERE login_id = ?1 AND password = ?2", nativeQuery = true)
	public Users getUserAuthData(String username, String password);

	@Modifying
	@Transactional
	@Query(value = "UPDATE users SET login_attempt_counter = login_attempt_counter + 1 WHERE login_id = ?1 AND (  SELECT name FROM role WHERE role_id = (SELECT role_id_fk FROM users WHERE login_id = ?1)) <> 'admin' AND is_locked = 0", nativeQuery = true)
	public int increaseLoginAttemptCounterBy1(String loginId);

	@Modifying
	@Transactional
	@Query(value = "UPDATE users SET login_attempt_counter = 0 WHERE login_id = ?1 AND (  SELECT name FROM role WHERE role_id = (SELECT role_id_fk FROM users WHERE login_id = ?1)) <> 'admin'", nativeQuery = true)
	public int resetLoginAttemptCounterBy1(String loginId);

	
	@Modifying
	@Transactional
	@Query(value = "UPDATE users SET is_locked = 1 WHERE login_id = ?1 AND login_attempt_counter >= ?2 AND is_locked = 0", nativeQuery = true)
	public int lockUserByAttemptCount(String loginId, int maxAttempt);

}
