package org.example.certifyproadmin.controller;

import org.example.certifyproadmin.model.UsuarioDAO; // Assumindo que o DAO existe
import org.example.certifyproadmin.model.Usuario;
import org.example.certifyproadmin.utils.JPAUtils;
import jakarta.persistence.EntityManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class ConfiguracaoAdminController {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @FXML private TextField nomeInstField;
    @FXML private TextField nomeAdminField;
    @FXML private TextField cpfAdminField;
    @FXML private TextField emailAdminField;
    @FXML private PasswordField senhaAdminField;

    public void cadastrarAdmin(ActionEvent event) {
        String nomeInst = nomeInstField.getText();
        String nomeAdmin = nomeAdminField.getText();
        String email = emailAdminField.getText();
        String senha = senhaAdminField.getText();
        String cpf = cpfAdminField.getText();

        if (nomeInst.isEmpty() || nomeAdmin.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Preencha todos os campos obrigatórios.");
            return;
        }

        EntityManager entityManager = null;

        try {
            entityManager = JPAUtils.getEntityManager();
            UsuarioDAO usuarioDAO = new UsuarioDAO(entityManager);

            String senhaCriptografada = passwordEncoder.encode(senha);

            Usuario usuarioBanco = new Usuario();
            usuarioBanco.setNome(nomeAdmin);
            usuarioBanco.setEmail(email);
            usuarioBanco.setSenha(senhaCriptografada);
            usuarioBanco.setCpf(cpf);
            usuarioBanco.setRole("ROLE_ADMIN");
            usuarioBanco.setNomeInstituicao(nomeInst);

            usuarioDAO.salvar(usuarioBanco);

            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Admin cadastrado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Falha ao Cadastrar", "Falha: O e-mail ou CPF já existem ou houve erro de conexão.");
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    private void mostrarAlerta(Alert.AlertType type, String titulo, String mensagem) {
        Alert alert = new Alert(type);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

}