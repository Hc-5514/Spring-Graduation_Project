package com.graduation.styleguide.repository;

import com.graduation.styleguide.domain.Subscribe;
import com.graduation.styleguide.domain.UploadInfo;
import com.graduation.styleguide.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {

    // 구독 여부 확인
    @Query(value = "SELECT COUNT(idx) FROM subscribe WHERE stylelist_idx = :stylelistId AND user_idx = :userId")
    int findSubscribeByStylelistIdxAndUserIdx(String userId, String stylelistId);

    // 구독하는 스타일리스트 수
    @Query(value = "SELECT COUNT(*) FROM subscribe WHERE user_idx = :UserId", nativeQuery = true)
    int findSubscribeCountByIdx(String UserId);

    // 스타일리스트의 구독자 수
    @Query(value = "SELECT COUNT(*) FROM subscribe WHERE stylelist_idx = :profileIdx", nativeQuery = true)
    int findSubscriberCountByIdx(String profileIdx);

    // 구독하는 스타일리스트 목록
    @Query(value = "SELECT stylelist_idx FROM subscribe WHERE user_idx = :UserId", nativeQuery = true)
    List<String> findSubscribeListbyUserId(String UserId);

    @Modifying
    @Query(value = "INSERT INTO subscribe(user_idx, stylelist_idx) VALUES(:userIdx, :stylelistIdx)", nativeQuery = true)
    void subscribe(String userIdx, String stylelistIdx);

    @Modifying
    @Query(value = "DELETE FROM subscribe WHERE user_idx = :userIdx AND stylelist_idx = :stylelistIdx", nativeQuery = true)
    void unSubscribe(String userIdx, String stylelistIdx);
}
