package cl.duocuc.ecomarket.control;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class EcomarketController {


    public abstract ResponseEntity<?> consultar(@PathVariable Long id);


}
