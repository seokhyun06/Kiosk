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

메소드를 생성하여 +버튼을 눌렀을 때 countm[n]+1 증가합니다// n == 메뉴 방 번호

## [-버튼]
![image](https://github.com/seokhyun06/Kiosk/assets/122009563/68bf3efd-6708-454f-ae0e-84c299e110f0)
- 버튼을 눌렀을 때 countm[n]의 갯수가 0보다 크면 -1 감소합니다

## [sum 버튼]

