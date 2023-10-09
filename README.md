# marine_back

## 개발시 주의사항
- resource/config 하위에 application-datasource.properties 생성 및 작성으로 datasource 설정 적용할것!

## 통신시 주의사항
-  1 : N 관계의 테이블 데이터 전송시, 테이블 명이 자바 모델의 필드값으로 사용되야함.
> ex ) 
>			product :
>				product_id : "",
>				origin : "",
>				price : {
>					price_id : "",
>						unit : ""
>				}
>			user :
>				user_id :"",
>				destination : {
>					destination_id : ""
>				}
- 주문 요청 시, Product 필드에 amount : "" 추가 해서 전송.
