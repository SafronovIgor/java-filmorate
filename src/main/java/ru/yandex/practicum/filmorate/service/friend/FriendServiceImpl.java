package ru.yandex.practicum.filmorate.service.friend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.ResourceNotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.repository.FriendRepository;
import ru.yandex.practicum.filmorate.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {
    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    @Override
    public List<User> getFriends(long id) {
        if (userRepository.existsById(id)) {
            return friendRepository.findAllFriends(id).stream()
                    .map(userRepository::findById)
                    .toList();
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }

    @Override
    public void addFriend(long userId, long friendId) {
        if (userRepository.existsById(userId) && userRepository.existsById(friendId)) {
            friendRepository.addFriend(userId, friendId);
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }

    @Override
    public void deleteFriend(long userId, long friendId) {
        if (userRepository.existsById(userId) && userRepository.existsById(friendId)) {
            friendRepository.deleteFriend(userId, friendId);
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }
}
