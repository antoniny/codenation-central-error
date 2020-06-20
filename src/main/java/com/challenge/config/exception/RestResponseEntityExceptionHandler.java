package com.challenge.config.exception;

import com.challenge.config.exception.dto.ExceptionHandlerResponseDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ NullPointerException.class })
    public ResponseEntity<ExceptionHandlerResponseDTO> handleNullPointerException(NullPointerException ex) {

         String mensagem = "Ocorreu uma falha no processamento de sua requisição.";
         String erro = "["+ex.getClass().getSimpleName()+"] "+ ex.getMessage();

        ExceptionHandlerResponseDTO handlerResponseDTO = new ExceptionHandlerResponseDTO(HttpStatus.BAD_REQUEST, mensagem , erro);

        return new ResponseEntity(handlerResponseDTO , HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler({ ConnectException.class })
    public ResponseEntity<ExceptionHandlerResponseDTO> handleConnectException(ConnectException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String mensagem = "Ocorreu uma falha de conexão durante o processamento de sua requisição.";
        String erro = "["+ex.getClass().getSimpleName()+"] "+ ex.getMessage();

        ExceptionHandlerResponseDTO handlerResponseDTO = new ExceptionHandlerResponseDTO(HttpStatus.BAD_REQUEST, mensagem , erro);

        return new ResponseEntity(handlerResponseDTO , HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler({ IllegalStateException.class })
    public ResponseEntity<ExceptionHandlerResponseDTO> handleIllegalStateException(IllegalStateException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String mensagem = "Ocorreu uma falha no processamento de sua requisição.";
        String erro = "["+ex.getClass().getSimpleName()+"] "+ ex.getMessage();

        ExceptionHandlerResponseDTO handlerResponseDTO = new ExceptionHandlerResponseDTO(HttpStatus.BAD_REQUEST, mensagem , erro);

        return new ResponseEntity(handlerResponseDTO , HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler({ NotFoundException.class })
    public ResponseEntity<ExceptionHandlerResponseDTO> handleNotFoundException(NotFoundException ex) {

        String mensagem = "Ocorreu uma falha no processamento de sua requisição.";
        String erro = "["+ex.getClass().getSimpleName()+"] "+ ex.getMessage();

        ExceptionHandlerResponseDTO handlerResponseDTO = new ExceptionHandlerResponseDTO(HttpStatus.NOT_FOUND, mensagem , erro);

        return new ResponseEntity(handlerResponseDTO , HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler({ SavePostException.class })
    public ResponseEntity<ExceptionHandlerResponseDTO> handleSavePostException(SavePostException ex) {

        String mensagem = "Ocorreu uma falha no processamento de sua requisição.";
        String erro = "["+ex.getClass().getSimpleName()+"] "+ ex.getMessage();

        ExceptionHandlerResponseDTO handlerResponseDTO = new ExceptionHandlerResponseDTO(HttpStatus.BAD_REQUEST, mensagem , erro);

        return new ResponseEntity(handlerResponseDTO , HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler({ JpaSystemException.class })
    public ResponseEntity<ExceptionHandlerResponseDTO> handleJpaSystemException(JpaSystemException ex) {

        String mensagem = "Ocorreu uma falha no processamento de sua requisição.";
        String erro = "["+ex.getClass().getSimpleName()+"] "+ ex.getMessage();

        ExceptionHandlerResponseDTO handlerResponseDTO = new ExceptionHandlerResponseDTO(HttpStatus.BAD_REQUEST, mensagem , erro);

        return new ResponseEntity(handlerResponseDTO , HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler({ ClassCastException.class })
    public ResponseEntity<ExceptionHandlerResponseDTO> handleClassCastException(ClassCastException ex) {

        String mensagem = "Ocorreu uma falha no processamento de sua requisição.";
        String erro = "["+ex.getClass().getSimpleName()+"] "+ ex.getMessage();

        ExceptionHandlerResponseDTO handlerResponseDTO = new ExceptionHandlerResponseDTO(HttpStatus.BAD_REQUEST, mensagem , erro);

        return new ResponseEntity(handlerResponseDTO , HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler({ DataIntegrityViolationException.class })
    public ResponseEntity<ExceptionHandlerResponseDTO> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {

        String mensagem = "Ocorreu uma falha no processamento de sua requisição. " + ex.getCause().getMessage();
        String erro = "["+ex.getClass().getSimpleName()+"] "+ ex.getMessage();

        ExceptionHandlerResponseDTO handlerResponseDTO = new ExceptionHandlerResponseDTO(HttpStatus.BAD_REQUEST, mensagem , erro);

        return new ResponseEntity(handlerResponseDTO , HttpStatus.BAD_REQUEST);

    }
/*
    @ExceptionHandler({ FeignException.class })
    public ResponseEntity<ExceptionHandlerResponseDTO> handleFeignException(IllegalStateException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String mensagem = "Ocorreu uma falha no processamento de sua requisição.";
        String erro = "["+ex.getClass()+"] "+ ex.getMessage();

        ExceptionHandlerResponseDTO handlerResponseDTO = new ExceptionHandlerResponseDTO(HttpStatus.BAD_REQUEST, mensagem , erro);

        return new ResponseEntity(handlerResponseDTO , HttpStatus.BAD_REQUEST);

    }
*/
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = ex.getParameterName() + " parâmetro é obrigatório.";

        ExceptionHandlerResponseDTO handlerResponseDTO = new ExceptionHandlerResponseDTO(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<Object>(handlerResponseDTO, new HttpHeaders(), handlerResponseDTO.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {String fieldName = ((FieldError) error).getField();
                                                                 String errorMessage = error.getDefaultMessage();
                                                                 errors.add(fieldName +" -> "+ errorMessage);
                                                                });

        ExceptionHandlerResponseDTO handlerResponseDTO = new ExceptionHandlerResponseDTO(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return new ResponseEntity<Object>(handlerResponseDTO, new HttpHeaders(), handlerResponseDTO.getStatus());
    }



    @ExceptionHandler({ Exception.class })
    public ResponseEntity<ExceptionHandlerResponseDTO> handleException(Exception ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String mensagem = "Ocorreu uma falha no processamento de sua requisição.";
        String erro = "["+ex.getClass().getSimpleName()+"] "+ ex.getMessage();

        ExceptionHandlerResponseDTO handlerResponseDTO = new ExceptionHandlerResponseDTO(HttpStatus.BAD_REQUEST, mensagem , erro);

        return new ResponseEntity(handlerResponseDTO , HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<ExceptionHandlerResponseDTO> handleRuntimeException(RuntimeException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String mensagem = "Ocorreu uma falha no processamento de sua requisição.";
        String erro = "["+ex.getClass().getSimpleName()+"] "+ ex.getMessage();

        ExceptionHandlerResponseDTO handlerResponseDTO = new ExceptionHandlerResponseDTO(HttpStatus.BAD_REQUEST, mensagem , erro);

        return new ResponseEntity(handlerResponseDTO , HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler({ UnexpectedTypeException.class , ConstraintViolationException.class })
    public ResponseEntity<ExceptionHandlerResponseDTO> handleUnexpectedTypeException(Exception ex) {

        String mensagem = "Ocorreu uma falha no processamento de sua requisição.";
        String erro = "["+ex.getClass().getSimpleName()+"] "+ ex.getMessage();

        ExceptionHandlerResponseDTO handlerResponseDTO = new ExceptionHandlerResponseDTO(HttpStatus.BAD_REQUEST, mensagem , erro);

        return new ResponseEntity(handlerResponseDTO , HttpStatus.BAD_REQUEST);

    }



}
