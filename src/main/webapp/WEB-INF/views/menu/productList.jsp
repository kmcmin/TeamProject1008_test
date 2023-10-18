<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>카테고리</title>
	<link rel="stylesheet" href="https://res.kurly.com/_next/static/css/d59287ec5b86dc49.css" data-n-g />
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
	<link rel="stylesheet" href="resources/css/cart.css">
	<link href="https://www.flaticon.com/free-icons/tool-tips" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
	<link rel="stylesheet" href="resources/css/toast.css">
<!-- <body> -->
	<div class="css-1pz4b76 er0tf675">
		<h2 class="css-1n2hipb er0tf674">신상품</h2>

		<div class="css-a6t9xs er0tf673">
			<div class="css-8wfj4z er0tf672">
				<div class="css-20o6z0 e149z643">
					<div class="css-zbxehx e149z642">
						<label class="css-1vf8o8s e1dcessg3"> 
							<input type="checkbox" class="css-agvwxo e1dcessg2">
							<span style="color:rgb(51, 51, 51); ">필터</span>
						</label>
						<span class="css-454d5e e149z641"></span>
						<button class="css-0 e149z640">초기화</button>
					</div>
					
					<div class="css-zbxehx e149z642" style="float: left;">
						<button class="css-0 e149z640" id="newProduct" onclick="reloadProduct(this.id)">신상품순</button>
						<span class="css-454d5e e149z641"></span>
						<button class="css-0 e149z640" id="manyProduct" onclick="reloadProduct(this.id)">판매량</button>
						<span class="css-454d5e e149z641"></span>
						<button class="css-0 e149z640" id="lowSalProduct" onclick="reloadProduct(this.id)">낮은가격순</button>
						<span class="css-454d5e e149z641"></span>
						<button class="css-0 e149z640" id="highSalProduct" onclick="reloadProduct(this.id)">높은가격순</button>
					</div>
				</div>
				
				<div id="productListData">
					<!-- *********************************	 한세트	*********************************-->
<!-- 					<div class="css-1t6so8j em21elb0" style="border-radius:5px; width:30%; height:400px; float: left; margin-right: 5px; margin-left: 5px;" onclick="movePage()"> -->
<!-- 						상품1 -->
<!-- 						<img src="resources/images/items/top1.gif" border="0" align="center" width="200"> -->
<!-- 						<div class="css-1t6so8j em21elb0"  -->
<!-- 							style="text-align: center; border-radius:5px; width:100%; height:18%; float: left; margin:5px; padding: 10px 15px 15px 5px; "> -->
<!-- 							<img src="resources/img/cha.png" style="width:10%;" > -->
<!-- 							<button style="center;">담기</button> -->
<!-- 						</div> -->
<!-- 						<span><p>상품설명</p></span> -->
<!-- 						<span><p>15000원</p></span> -->
<!-- 					</div>
					<!-- *********************************	 한세트	*********************************-->
				</div>
<!-- 				<div class="css-20o6z0 e149z643"> -->
<!-- 					<div class="css-zbxehx e149z642"> -->
<!-- 						<label class="css-1vf8o8s e1dcessg3" disabled> <input -->
<!-- 							type="checkbox" disabled class="css-agvwxo e1dcessg2" /> -->
<!-- 							<div class="css-79hxr7 e1dcessg1"> -->
<!-- 								<svg width="24" height="24" viewBox="0 0 24 24" fill="none" -->
<!-- 									xmlns="http://www.w3.org/2000/svg"> -->
<!--                                 <path -->
<!-- 										d="M23.5 12C23.5 18.3513 18.3513 23.5 12 23.5C5.64873 23.5 0.5 18.3513 0.5 12C0.5 5.64873 5.64873 0.5 12 0.5C18.3513 0.5 23.5 5.64873 23.5 12Z" -->
<!-- 										stroke="#ddd" fill="#f2f2f2"></path> -->
<!--                                 <path d="M7 12.6667L10.3846 16L18 8.5" -->
<!-- 										stroke="#ddd" stroke-width="1.5" stroke-linecap="round" -->
<!-- 										stroke-linejoin="round"></path> -->
<!--                             </svg> -->
<!-- 							</div> <span>전체선택 (0/0)</span> -->
<!-- 						</label> <span class="css-454d5e e149z641"></span> -->
<!-- 						<button disabled class="css-0 e149z640">선택삭제</button> -->
<!-- 					</div> -->
<!-- 				</div> -->
			</div>
			<div class="css-1dta0ch er0tf671">
				<div class="css-50ad8x er0tf670" id="pet">
					<div class="css-1t6so8j em21elb0">
						<div class="css-8jmoub ea1mry77">
							<span class="css-vmo0an ea1mry76">상품금액</span>
							<span class="css-iinokh ea1mry74" id="salVal"> 0
 							<span class="css-hfgifi ea1mry72">원</span>
							</span>
						</div>
						<div class="css-t4mc5m ea1mry77">
							<span class="css-vmo0an ea1mry76">상품할인금액</span> <span
								class="css-iinokh ea1mry74"> 0<span
								class="css-hfgifi ea1mry72">원</span>
							</span>
						</div>
<!-- 						<div class="css-t4mc5m ea1mry77"> -->
<!-- 							<span class="css-vmo0an ea1mry76">배송비</span> <span -->
<!-- 								class="css-iinokh ea1mry74"> 0<span -->
<!-- 								class="css-hfgifi ea1mry72">원</span> -->
<!-- 							</span> -->
<!-- 						</div> -->
						<div class="css-7ygxxm eepcpbj4">
							<span class="css-vmo0an eepcpbj3">결제예정금액</span>

							<span class="css-da7gr8 eepcpbj2">
								 <strong class="css-xmbce4 eepcpbj0">0</strong>
								 <span class="css-aro4zf eepcpbj1">원</span>
							</span>
						</div>
					</div>

					<div class="css-8qz8ia e1mybczi1">

						<button class="css-fwelhw e4nu7ef3" type="button" onclick="" height="56">
							<span class="css-13budib e4nu7ef1">구매하기</span>
						</button>
						<ul class="css-19kxq7d">
							<li class="css-1741abm ejr204i0">[주문완료] 상태일 경우에만 주문 취소 가능합니다.</li>
							<li class="css-1741abm ejr204i0">[파머스 &gt;주문내역 상세페이지] 에서 직접 취소하실 수 있습니다.</li>
						</ul>
						
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		
		var salMoney = 0;
	
		//화면 진입시 호출하는 이벤트 모음
		$(document).ready(function() {
			getProductList();
		});
	
		//페이지 이동, 테스트중 
		function movePage() {
// 			location.href='http://localhost:8093/app/main';
		};
		//상품리스트 출력
		function getProductList(val){
			//상품리스트 초기화
// 			$("#productListData").html();
			
			var setParam = {};
// 			console.log("aa",val)
			if(val != null){
				setParam.filter = val; 
			}
			//리스트 
			$.ajax({
				type : "POST",
				url : "getProductList",
				dataType : "json",
				data : setParam,
				success : function(rdata, status, xhr) {
					console.log("리스트받아와지나 : "+rdata);
					
					//전체 DIV태그를 만드는 변수
					var productDiv = "";
					//상품 스타일
					var productStyle = "border-radius:5px; width:30%; height:400px; float: left; margin-right: 5px; margin-left: 5px;";
					//상품 담기 스타일
					var productSaveStyle = "text-align: center; border-radius:5px; width:100%; height:12%; float: left; margin:5px; padding: 10px 15px 15px 5px;";
					//전체 상품에 대한 루프
					for (var i = 0; i < rdata.length; i++) {
						productDiv += "<div class='css-1t6so8j em21elb0' style='"+productStyle+"'>";
						productDiv += rdata[i].PRODUCT_NAME;
						var productCd = "'"+rdata[i].PRODUCT_CODE+"'";
						productDiv += '<img src="resources/images/items/'+rdata[i].PRODUCT_IMG+'.gif" border="0" align="center" onclick="productDetail('+productCd+')" width="200">';
						productDiv += "<div class='css-1t6so8j em21elb0' style='"+productSaveStyle+"'>";
						productDiv += "	<img src='resources/img/cha.png' style='width:10%;' >";
						var productPrice = "'"+rdata[i].PRODUCT_PRICE+"'";
// 						productDiv += '<button style="center;" onclick="productSave('+productPrice+','+i+')">담기</button>';
						productDiv += '<button style="center;" onclick="productSave('+productPrice+','+i+','+productCd+')">담기</button>';
						productDiv += '<div id="toast" name="toastNm'+i+'"> '+rdata[i].PRODUCT_NAME+'을 담았습니다.</div>';
						productDiv += "</div>";
						productDiv += "<span><p>"+rdata[i].PRODUCT_CONTEXT+"</p></span>";
						productDiv += "<span><p>"+rdata[i].PRODUCT_PRICE+"원</p></span>";
						productDiv += "</div>";
					}
					$("#productListData").html(productDiv);
					
				}, 
				error : function(xhr, status, error) {
					console.log("error");
					return false;
				}
			});
		}
		

// 		// 장바구니
// 		function productSave(val, numVal) {
// 			val = parseInt(val);
// 			salMoney += val;
// 			$("#salVal").text(salMoney.toLocaleString());
			
// 			var showId = "toastNm"+numVal;
			
// 			// 아래 테스트중 상품담으면 토스트 메세지 on
// 			const toast = document.getElementsByName(showId);
// 			let isToastShown = false;
// 			if (isToastShown) return;   // 토스트 메시지가 띄어져 있다면 함수를 끝냄
// 			isToastShown = true;
// 			var addClass = toast[0];
// 			addClass.classList.add('show');    // show라는 클래스를 추가해서 토스트 메시지를 띄우는 애니메이션을 발동시킴
// 	            setTimeout(function () {
// 	                // 2700ms 후에 show 클래스를 제거함
// 	                addClass.classList.remove('show'); 
// 	                isToastShown = false;
// 	            }, 2700);
			
// 		};
		 function productSave(val, numVal, proCd) {
         
         var setParam = {}
         setParam.product_img = "";         //왜넘김? 
         setParam.product_code = proCd;       //상품코드
         setParam.cart_count = 1;          //숫자무조건 한개씩넘김 갯수를 조절하는 부분이 없기때문임
         setParam.product_name = "";       //왜넘김? 
         setParam.product_context = "";       //왜넘김? 
         <%if (session.getAttribute("login")==null){%>
            alert("로그인이 필요합니다");
            return false;
         <%}%>
         $.ajax({
            type : "POST",
            url : "cartAdd",
            dataType : "text",
            data : setParam,
            success : function(rdata, status, xhr) {
               val = parseInt(val);
               salMoney += val;
               $("#salVal").text(salMoney.toLocaleString());
               var showId = "toastNm"+numVal;
               
               // 아래 테스트중 상품담으면 토스트 메세지 on
               const toast = document.getElementsByName(showId);
               let isToastShown = false;
               if (isToastShown) return;   // 토스트 메시지가 띄어져 있다면 함수를 끝냄
               isToastShown = true;
               var addClass = toast[0];
               addClass.classList.add('show');    // show라는 클래스를 추가해서 토스트 메시지를 띄우는 애니메이션을 발동시킴
                     setTimeout(function () {
                         // 2700ms 후에 show 클래스를 제거함
                         addClass.classList.remove('show'); 
                         isToastShown = false;
                     }, 2700);
            }, 
            error : function(xhr, status, error) {
               console.log("error");
               return false;
            }
         });
      };



		// 필터값 new/many/high/low, 중에 하나 받아서 리스트 출력
		function reloadProduct(id){
			var param = "";
			if(id == "manyProduct"){
				param = "many";
			}else if(id == "lowSalProduct"){
				param = "low";
			}else if(id == "highSalProduct"){
				param = "high";
			}
			getProductList(param);
		}
		
		//테스트중
		function productDetail(val) { 
			var param = val;
			location.href="productDetails?product_code="+param;
// 			<a href="productDetails?product_code=${goods.product_code}">

		};
		
		
	</script>
<!-- </body> -->

</html>