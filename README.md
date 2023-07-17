# Kiosk
## [주문화면]
![image](https://github.com/seokhyun06/Kiosk/assets/122009563/2f0ea3d1-0728-4f9d-bb5c-d03c4338c11b)

## [관리자 로그인 화면]
![image](https://github.com/seokhyun06/Kiosk/assets/122009563/fe45509a-20e5-470c-8131-f36d2308ccb7)

## [관리자 화면]
![image](https://github.com/seokhyun06/Kiosk/assets/122009563/9b7ae80f-0de1-4b90-9122-b352e4c6be4b)

# 코드 설명
## [변수 생성]
![image](https://github.com/seokhyun06/Kiosk/assets/122009563/af267a55-c8ac-427e-b474-7486ffc8126d)

## [+버튼]
``` java
    private void menu_append() {		
    	ListTextArea.setText("");
    	for(int i=0; i<3;i++) {
    		ListTextArea.appendText(menu_name[i] + " : " + countm[i] + "잔"+"\n");
    	}	
    }
    
    @FXML
    public void M1pButtonAction(ActionEvent event) {
    	countm[0]=countm[0]+1;
    	menu_append();
    }


	@FXML
	public void M2pButtonAction(ActionEvent event) {
    	countm[1]++;    	
    	menu_append();
    }
    
    @FXML
    public void M3pButtonAction(ActionEvent event) {
    	countm[2]++;    	
    	menu_append();
    }
```
메소드를 생성하여 +버튼을 눌렀을 때 countm[n]+1 증가합니다// n == 메뉴 방 번호

## [-버튼]
``` java
   @FXML
    public void M1mButtonAction(ActionEvent event) {
    	if(countm[0]>0) countm[0]--;
    	menu_append();
    }

    @FXML
    public void M2mButtonAction(ActionEvent event) {
    	if(countm[1]>0) countm[1]--;
    	menu_append();
    }
    
    @FXML
    public  void M3mButtonAction(ActionEvent event) {
    	if(countm[2]>0) countm[2]--;
    	menu_append();
    }
```    
    
버튼을 눌렀을 때 countm[n]의 갯수가 0보다 크면 -1 감소합니다

## [취소 버튼]
``` java
  @FXML
    public void CancleButtonAction(ActionEvent event) {
    	sumLabel.setText("0");
    	ListTextArea.setText("");
    	for(int i=0;i<3; i++) {
    		countm[i]=0;
    	}	
    }
```
취소버튼을 눌렀을 때 모든 변수방, 합계 금액이 나오는 화면 초기화를 합니다
## [sum 버튼]
``` java
    @FXML
    public void SumButtonAction(ActionEvent event) {
    	sum = kiosksum.ksum(countm);
    	sumLabel.setText(sum + "");
    }
```
sum은 정수형인데 sumtext가 문자형이기 때문에 ""를 추가해 문자형으로 바뀐다.

## [주문하기 버튼]
``` java
 @FXML
    public void OrderButtonAction(ActionEvent event) {
    	
    	if(sum != 0) {
    		// 디비 접속
    		DBconnect3 conn = new DBconnect3();
    		Connection conn2 = conn.getConnection();
    		String sql = "Insert into orderlist_accounts"
    				+ " (idx, order_time, menu1, count1, menu2, count2, menu3, count3, sum)"
    				+ " values (orderlist_idxpk.nextval, current_timestamp, '아메리카노', ?, '카푸치노', ?, '카페라떼', ?, ?)";
    		
    		try {
				PreparedStatement ps = conn2.prepareStatement(sql);
				ps.setInt(1, countm[0]);
				ps.setInt(2, countm[1]);
				ps.setInt(3, countm[2]);
				ps.setInt(4, sum);
				
				ResultSet rs = ps.executeQuery();
				
				// rs값이 있으면 
					// 주문 성공 메세지 띄우기
					// 화면에 보이는 내용 초기화, 실제 변수값도 초기화
				
				if(rs.next()) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setContentText("주문성공 하셨습니다.");
					alert.show();
					
					ListTextArea.setText("");
					sumLabel.setText("0");
					sum = 0;
					countm[0] = 0;
					countm[1] = 0;
					countm[2] = 0;
				}
```
- 합계 버튼이 0이 아니면 DB에 접속하기 위해 클래스를 생성한다.
- SQL문을 작성하고 ps에 저장한다.
- SQL문의 ?자리에 주문메뉴와 합계를 넣어준다.
- rs에 저장된 값이 있으면 즉 로그인이 성공하면 주문 성공 메세지, 화면에 보이는 내용을 초기화 시킨다.

## [경고메세지]
``` java
} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setContentText("죄송합니다, 카운터에 문의해주세요.");
					alert.show();
				}
    		
    		} catch (SQLException e) {
    			
				e.printStackTrace();
			}
```
rs에 저장된 값이 없으면 경고메세지를 띄운다.

# [관리자 로그인 화면에 있는 로그인 버튼]
``` java @FXML
	private void LoginButtonAction(ActionEvent event) {
		if(IdTextField.getText().isEmpty() || PwPasswordField.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("경고");
			alert.setHeaderText("메시지");
			alert.setContentText("아이디, 비번 다시 입력하세요");
			alert.show();
		
		}
```
아이디 칸 이나 비밀번호 칸이 비어있으면 경고메세지 출력한다.

``` java
else {
			// DB 접속
			// DB에 있는 것과 사용자가 입력한 아이디와 비번이 맞는지 확인하기
		DBconnect3 conn = new DBconnect3();
		Connection conn2 = conn.getConnection();
		
		// 관리자 테이블에서 사용자가 입력한 값과 같은 아이디와 비번을 검색하는 sql문을 작성
		String sql = "select adminid, adminpw"
				+ " from admin_accounts"
				+ " where adminid = ?"
				+ " and adminpw = ?";
```
그 외에는 DB에 접속하고 관리자 테이블에서 사용자가 입력한 값과 같은 아이디와 비번을 검색하는 sql문을 작성한다.

## [닫기 버튼]
``` java
@FXML
	private void CloseButtonAction(ActionEvent event) {
		Stage stage = (Stage) CloseButton.getScene().getWindow();
		stage.close();
	}
```

# [관리자 화면]
![image](https://github.com/seokhyun06/Kiosk/assets/122009563/e5472828-11f7-4ea8-bfa9-67366306a084)
- Orderlist라는 클래스를 만들어서 생성자, 게터, 세터를 지정한 후 다음 각 열의 이름을 지정한다.
- 이니셜라이즈를 통해 코드가 실행되는 순간 바로 실행 DB연동을 통한 테이블 열에 데이터를 삽입한다.

## [전체 조회 버튼]
``` java
		// 주문리스트 테이블에 있는 자료 검색하기(정렬기준 idx)
			String sql = "select idx, to_char(order_time, 'yyyy-mm-dd hh24:mi:ss'), count1, count2, count3, sum"
					+ " from orderlist_accounts"
					+ " order by idx";
			
			try {
				PreparedStatement ps = conn2.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				
				ObservableList<Orderlist> datalist = FXCollections.observableArrayList();
					
				resultTextArea.setText("");
				mcount1 = 0;
				mcount2 = 0;
				mcount3 = 0;
				msum = 0;
				
```
- 전체조회 버튼을 클릭시 DB에 접속하고 SQL문을 실행하여 결과값을 rs에 저장한다.
- 값을 누적하여 저장한 뒤 출력 할 arraylist를 생성한다.
- 동시에 누를때마다 결과값을 초기화 시킨다.

## [날짜 조회 버튼]
``` java
// dateDatePicker에 있는 날짜에 해당하는 데이터를 검색해
				String sql = "select idx, order_time, count1, count2, count3, sum"
						+ " from orderlist_accounts"
						+ " where to_char(order_time, 'yyyy-mm-dd') = ?";
```
to_char을 쓴 이유는 DB에 저장되는 값은 시, 분, 초 이므로 to_char를 사용해 일까지만 끊어서 SQL문을 작성한다.

## [기간별 조회 버튼]
``` java
				String sql = "select idx, to_char(order_time, 'yyyy-mm-dd hh24:mi:ss'),  count1, count2, count3, sum"
						+ " from orderlist_accounts"
						+ " where order_time >= ? and order_time <= ?"
						
						//+ " where order_time between ? and ?"
						// 주문일시가 A 날짜부터 B 날짜 사이인 경우
						+ "	order by idx";

```
- 조건식은 두 가지로 나뉘는 데, between함수와 부등호를 활용한 것으로 나뉜다.
- 첫번째는 between함수를 사용한 조건식이다.
- 주문일시가 A 날짜부터 B사이인 경우니까 between함수를 사용한다.
- 두번째는 부등호를 사용한 조건식이다.
- 주문일시가 ?보다 크거나 같거나 그리고 주문일시가 ?보다 작거나 같은 경우이다.
- DB에 저장된 값은 몇시 몇분 몇초 이기 때문에 당일은 잡히지 않는다 따라서 1일을 더해줌으로써 조회가 가능하게 된다. 


