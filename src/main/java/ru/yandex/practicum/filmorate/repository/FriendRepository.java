package ru.yandex.practicum.filmorate.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FriendRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<Long> findAllFriends(Long userId) {
        String sql = "SELECT friend_id FROM \"friends\" WHERE user_id = ?";

        return jdbcTemplate.query(sql,
                new Object[]{userId},
                (rs, rowNum) -> rs.getLong("friend_id"));
    }


    public void addFriend(Long userId, Long friendId) {
        String sql = """
                INSERT INTO "friends" (user_id, friend_id, status_id) VALUES (?, ?, ?)
                """;
        jdbcTemplate.update(sql, userId, friendId, 1);
    }

    public void deleteFriend(Long userId, Long friendId) {
        String sql = """
                DELETE FROM "friends" WHERE user_id = ? AND friend_id = ?
                """;
        jdbcTemplate.update(sql, userId, friendId);
    }
}
