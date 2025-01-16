package app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Carro;
import app.repository.CarroRepository;

@Service
public class CarroService {
	
	@Autowired
	private CarroRepository carroRepository;
	
	public String save(Carro carro) {
		
		this.carroRepository.save(carro);
		
		return "Carro salvo com sucesso!";
	}
	
	public Carro findByid(Long  id) {
		
	Optional<Carro> carro =	this.carroRepository.findById(id);
	return carro.get();
		
	}
	
	public List<Carro> findAll() {
		return this.carroRepository.findAll();
		
	}
	
	
	public Object updateById(Long id, Carro carroAtt) {
	    Optional<Carro> carroExistente = this.carroRepository.findById(id);
	    
	    if (carroExistente.isPresent()) {
	        Carro carro = carroExistente.get();

	        carro.setMarca(carroAtt.getMarca());
	        carro.setModelo(carroAtt.getModelo());
	        carro.setAnoFabricacao(carroAtt.getAnoFabricacao());
	        carro.setNome(carroAtt.getNome());


	        this.carroRepository.save(carro);
	        return "Carro atualizado com sucesso!";
	    } else {
	        return "Carro não encontrado com o ID: " + id;
	    }
	}

	
	public String  deleteById(Long id) {
		  Optional<Carro> carro = this.carroRepository.findById(id);
		
		  if (carro.isPresent()) {
		        this.carroRepository.deleteById(id);
		        return "Carro excluído com sucesso!";
		    } else {
		        return  ("Carro não encontrado com o ID: " + id);
		    }
	}

}
