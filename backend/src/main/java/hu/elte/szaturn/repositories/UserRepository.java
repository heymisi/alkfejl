/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.szaturn.repositories;

import hu.elte.szaturn.entities.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author KeresztiKrisztián
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByNeptunCode(String neptunCode);
}
