package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Carro;
import app.service.CarroService;

@RestController
@RequestMapping("/carro")
public class CarroController {

	@Autowired
	private CarroService carroService;

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Carro carro){
		
		try {
			String mensagem = this.carroService.save(carro);
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Deu algo errado!", HttpStatus.BAD_REQUEST);
		}
		
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Carro> findById(@PathVariable Long id){
		
		try {
			Carro carro = this.carroService.findByid(id);
			return new ResponseEntity<> (carro, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
	}
	
    @GetMapping("/findAll")
    public ResponseEntity<List<Carro>> findAll() {
    	
        try {
            // Chama o serviço para obter a lista de carros
            List<Carro> carros = this.carroService.findAll();
            return new ResponseEntity<>(carros, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
 
	}
    
    @PutMapping("/updateById/{id}")
    public  ResponseEntity<Object> updateById(@PathVariable Long id, @RequestBody Carro carroAtt){
    	try {
    		Object atualizarCarro = this.carroService.updateById(id, carroAtt);
    		
    		if( atualizarCarro instanceof String) {
    			 return new ResponseEntity<>(atualizarCarro, HttpStatus.NOT_FOUND);
    		}else {
    			return new ResponseEntity<>(atualizarCarro, HttpStatus.OK);
    		}
			
		} catch (Exception e) {
			return new ResponseEntity<>("Erro ao atualizar o carro.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	
    }
    
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        try {
            String carro = this.carroService.deleteById(id);
            return new ResponseEntity<>(carro, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao excluir o carro.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}




