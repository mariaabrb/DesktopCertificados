package org.example.certifyproadmin.controller;

import org.example.certifyproadmin.model.CriarInstituicaoDto;
import org.example.certifyproadmin.service.ApiService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class CriarAdminController {

    @FXML private TextField nomeInstField;
    @FXML private TextField nomeAdminField;
    @FXML private TextField cpfAdminField;
    @FXML private TextField emailAdminField;
    @FXML private PasswordField senhaAdminField;

    private final ApiService apiService;

    public CriarAdminController() {
        this.apiService = new ApiService();
    }

    @FXML
    public void handleSalvar(ActionEvent event) {
        if (nomeInstField.getText().isEmpty() || emailAdminField.getText().isEmpty() || senhaAdminField.getText().isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Preencha todos os campos obrigatórios.");
            return;
        }

        CriarInstituicaoDto dto = new CriarInstituicaoDto(
                nomeInstField.getText(),
                nomeAdminField.getText(),
                emailAdminField.getText(),
                senhaAdminField.getText(),
                cpfAdminField.getText()
        );

        boolean sucesso = apiService.criarInstituicao(dto);

        if (sucesso) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso!", "Administrador criado! Você já pode fechar o Desktop e logar na Web.");
            limparCampos();
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Falha", "Não foi possível criar. Verifique o console do backend (409 CONFLICT se já existir Admin).");
        }
    }

    private void limparCampos() {
        nomeInstField.clear();
        nomeAdminField.clear();
        cpfAdminField.clear();
        emailAdminField.clear();
        senhaAdminField.clear();
    }

    private void mostrarAlerta(Alert.AlertType type, String titulo, String mensagem) {
        Alert alert = new Alert(type);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}