package hu.elte.szaturn.controllers;


import hu.elte.szaturn.entities.Room;
import hu.elte.szaturn.repositories.RoomRepository;
import hu.elte.szaturn.security.AuthenticatedUser;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author KeresztiKrisztián
 */
@CrossOrigin
@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private AuthenticatedUser authenticatedUser;

    @GetMapping("")
    public ResponseEntity<Iterable<Room>> getAll() {
        return ResponseEntity.ok(roomRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> get(@PathVariable Integer id) {
        Optional<Room> room = roomRepository.findById(id);
        if (room.isPresent()) {
            return ResponseEntity.ok(room.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Room> post(@RequestBody Room room) {
        Room savedCourse = roomRepository.save(room);
        return ResponseEntity.ok(savedCourse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> put(@RequestBody Room room, @PathVariable Integer id) {
        Optional<Room> oCourse = roomRepository.findById(id);
        if (oCourse.isPresent()) {
            room.setId(id);
            return ResponseEntity.ok(roomRepository.save(room));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Room> oCourse = roomRepository.findById(id);
        if (oCourse.isPresent()) {
            roomRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

