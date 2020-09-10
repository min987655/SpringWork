package com.project.brunch.domain.follow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FollowRepository extends JpaRepository<Follow, Integer> {

	@Query(value = "SELECT count(*) FROM follow WHERE toUserId = ?1", nativeQuery = true)
	int mCountByFollower(int toUserId);

	@Query(value = "SELECT count(*) FROM follow WHERE fromUserId = ?1", nativeQuery = true)
	int mCountByFollowing(int fromUserId);

	@Query(value = "SELECT count(*) FROM follow WHERE fromUserId = ?1 and toUserId = ?2", nativeQuery = true)
	int mFollowState(int loginUserId, int pageUserId);
}
