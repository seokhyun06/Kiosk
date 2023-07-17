package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AdminloginController {

	@FXML Button LoginButtion, ClearButton, CloseButton;
	@FXML TextField IdTextField;
	@FXML PasswordField PwPasswordField;
	
	
	@FXML
	private void LoginButtonAction(ActionEvent event) {
		if(IdTextField.getText().isEmpty() || PwPasswordField.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("경고");
			alert.setHeaderText("메시지");
			alert.setContentText("아이디, 비번 다시 입력하세요");
			alert.show();
		
		} else {
			// DB 접속
			// DB에 있는 것과 사용자가 입력한 아이디와 비번이 맞는지 확인하기
		DBconnect3 conn = new DBconnect3();
		Connection conn2 = conn.getConnection();
		
		// 관리자 테이블에서 사용자가 입력한 값과 같은 아이디와 비번을 검색하는 sql문을 작성
		String sql = "select adminid, adminpw"
				+ " from admin_accounts"
				+ " where adminid = ?"
				+ " and adminpw = ?";
		
		
		try {
			PreparedStatement ps = conn2.prepareStatement(sql);
			ps.setString(1, IdTextField.getText());
			ps.setString(2, PwPasswordField.getText());
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) { 
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("로그인 성공");
				alert.show();
				
//				stage = (Stage)CloseButton.getScene().getWindow();
//				stage.close();
				
				CloseButtonAction(event);
				
				try {
					Parent root = FXMLLoader.load(getClass().getResource("Admindb.fxml"));
					Stage stage = new Stage();
					Scene scene = new Scene(root);
					stage.setTitle("관리자 화면");
					stage.setScene(scene);
					stage.show();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			
			}
			 else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("로그인 실패");
				alert.show();
			 	}
			
			} catch (SQLException e) {
				e.printStackTrace();
	
				
			}
		
		}
	
	}
	
	@FXML
	private void ClearButttonAction(ActionEvent event) {
		IdTextField.setText("");
		PwPasswordField.setText("");
		
	}
	
	
	@FXML
	private void CloseButtonAction(ActionEvent event) {
		Stage stage = (Stage) CloseButton.getScene().getWindow();
		stage.close();
	}
	
	
	
}
