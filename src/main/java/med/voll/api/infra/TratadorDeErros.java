package med.voll.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404(){
    return ResponseEntity.notFound().build();
    }

//    tratar erro que o  beanvalidation manda quando nao mandam os argumentos corretos
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros);
    }
    //código omitido

    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
//            passamos o erro inteiro para esse metodo e ele pega apenas dois campos que precisamos que é o olocal onde ta dando erro e a mensagem de erro
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

}
