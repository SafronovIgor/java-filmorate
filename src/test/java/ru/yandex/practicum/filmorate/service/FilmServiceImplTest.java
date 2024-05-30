//package ru.yandex.practicum.filmorate.service;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import ru.yandex.practicum.filmorate.exception.ResourceNotFoundException;
//import ru.yandex.practicum.filmorate.model.Film;
//import ru.yandex.practicum.filmorate.service.film.FilmServiceImpl;
//import ru.yandex.practicum.filmorate.storage.film.InMemoryFilmStorage;
//
//import java.time.LocalDate;
//import java.util.Collection;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class FilmServiceImplTest {
//
//    private FilmServiceImpl filmServiceImpl;
//
//    @BeforeEach
//    void setUp() {
//        filmServiceImpl = new FilmServiceImpl(new InMemoryFilmStorage());
//    }
//
//    @Test
//    void testGetFilms() {
//        Collection<Film> films = filmServiceImpl.getFilms();
//        assertNotNull(films);
//        assertTrue(films.isEmpty());
//    }
//
//    @Test
//    void testAssignNewId() {
//        var film = new Film();
//        filmServiceImpl.assignNewId(film);
//        assertNotEquals(0, film.getId());
//    }
//
//    @Test
//    void testAddUserToMap() {
//        var film = new Film();
//        filmServiceImpl.assignNewId(film);
//        film.setName("Test Film");
//        film.setDescription("Test Description");
//        film.setReleaseDate(LocalDate.now());
//        film.setDuration(120);
//
//        filmServiceImpl.addFilmToMap(film);
//
//        Collection<Film> films = filmServiceImpl.getFilms();
//        assertEquals(1, films.size());
//        assertTrue(films.contains(film));
//    }
//
//    @Test
//    void testCheckUserExists() {
//        assertThrows(ResourceNotFoundException.class, () -> filmServiceImpl.checkFilmExists(1L));
//
//        var film = new Film();
//        filmServiceImpl.assignNewId(film);
//        filmServiceImpl.addFilmToMap(film);
//
//        assertDoesNotThrow(() -> filmServiceImpl.checkFilmExists(1L));
//    }
//
//    @Test
//    void testAddLike() {
//        var film = new Film();
//        filmServiceImpl.assignNewId(film);
//        filmServiceImpl.addFilmToMap(film);
//
//        long beforeLikes = film.getLikes().size();
//        filmServiceImpl.addLike(1L, film);
//        long afterLikes = film.getLikes().size();
//
//        assertEquals(beforeLikes + 1, afterLikes);
//        assertTrue(film.getLikes().contains(1L));
//    }
//
//    @Test
//    void testRemoveLike() {
//        var film = new Film();
//        filmServiceImpl.assignNewId(film);
//        filmServiceImpl.addFilmToMap(film);
//        filmServiceImpl.addLike(1L, film);
//
//        assertTrue(film.getLikes().contains(1L));
//
//        filmServiceImpl.removeLike(1L, film);
//        assertFalse(film.getLikes().contains(1L));
//    }
//
//    @Test
//    void testGetTopPopularFilms() {
//        for (int i = 0; i < 11; i++) {
//
//            var film = new Film();
//            filmServiceImpl.assignNewId(film);
//
//            for (int j = 0; j < i; j++) {
//                filmServiceImpl.addLike(j, film);
//            }
//
//            filmServiceImpl.addFilmToMap(film);
//        }
//
//        List<Film> films = filmServiceImpl.getTopPopularFilms(10);
//        assertEquals(10, films.size());
//    }
//}
