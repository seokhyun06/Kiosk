# Kiosk
## [주문화면]
![image](https://github.com/seokhyun06/Kiosk/assets/122009563/2f0ea3d1-0728-4f9d-bb5c-d03c4338c11b)

## [관리자 로그인 화면]
![image](https://github.com/seokhyun06/Kiosk/assets/122009563/fe45509a-20e5-470c-8131-f36d2308ccb7)

## [관리자 화면]
![image](https://github.com/seokhyun06/Kiosk/assets/122009563/3e3dc4b9-23b1-4ec6-a5dc-046b36329c30)

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
    
- 버튼을 눌렀을 때 countm[n]의 갯수가 0보다 크면 -1 감소합니다

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
- 

