# Mô phỏng Đa tác tử
***
***Tác giả: BuiDuc15***
***
## Bài toán đặt ra:

Xây dựng một nhóm tác tử (ví dụ từ 2 tới 8 tác tử) mô phỏng “máy hút bụi” có nhiệm vụ“dọn dẹp” căn phòng 
(diện tích tùy chọn, có và không có vật cản). Môi trường hoạt động có hoặc không có thay đổi trong quá trình 
hoạt động (dynamic  environment).Nhóm  tác  tử cần giao  tiếp  và phối  hợp  hoạt động với nhau theo cách 
nào đó để dọn dẹp phòng cho sạch trong khoảng thời gian hoặc bước đi ít nhất. 
Phòng được dọn dẹp sạch sẽ khi tất cả các bề mặt sàn của phòng được ít nhất một tác tử đi qua ít nhất 1 lần.

## Giải pháp bài toán:
Để giải quyêt bài toán, chương trình sử dụng thuật toán Backtracking.
### Thuật toán Backtracking: 
Với mỗi bước đi, tác tử đều ghi lại hành trình của mình. Đến khi không có khả năng cho bước đi kế tiếp , tác tử sẽ
quay ngược lại những hành trình cũ của mình cho tới khi tim được bước đi kế tiếp có khả năng di chuyển. 
### Bước di chuyển của một tác tử
- Trước khi di chuyển, tác tử sẽ ghi lại vị trí bắt đầu của mình vào danh sach bước đi (BacktrackList).
- Tác tử sẽ tìm bước đi mới của mình (checkNewStep) theo tự duyệt ưu tiên: Đi lên, Rẽ phải, Đi xuống, Rẽ trái.
Tác tử di chuyển
- Đang trong hướng di chuyển, nếu hướng di chuyển đó cho bước đi kế tiếp có khả năng (Không bị cản, không chạm mép tường,
không đi vào vị trí đã đi qua) thì tác tử tiếp tục di chuyển. Nếu bước đi kế tiếp đó không khả năng, tác tử sẽ checkNewStep.
- Tác tử cứ lặp lại quá trình cho tới khi tới ô vị trí bắt đầu và ô này không còn trường hợp di chuyển nào có khả năng
- **Kết thúc**
### Bài toán với đa tác tử
- Các tác tử sẽ di chuyển độc lập theo các bước di chuyển trên. 
- Tuy nhiên các tác tử sẽ trao đổi với nhau về trạng thái các ô trong bản đồ thông qua 1 mảng squares chứa các
phần tử square gồm trạng thái của ô đó.
- Căn phòng được dọn dẹp xong khi tất cả các tác tử quay về vị trí bắt đầu của chính nó.

## Các giao diện
### Ví dụ
1. Bước khởi tạo
- Khởi tạo bản đồ với ma trận có kích thước 8x7 <=> Chiều ngang phòng là 7 chiều sâu là 8.
- Số lượng tường xuất hiện. Mỗi tường được khởi tạo từ vị trí gốc của tường và các chiều dài, chiều rộng của tường.
Vị trí gôc của tường được random 2 chiều x và y sao cho là không âm, nhỏ hơn chiều ngang và chiều sâu của bản đồ.
Chiều dài và chiều rộng tường được random trong 3 số (0,1,2).
- Vị trí bắt đầu của các robot. Bài toán ví dụ là vị trí của Robot 1 có tọa độ là x=3, y=3; Robot 2 có tọa độ là 
x=1, y=2.
- Sau khi chọn Initilize. Bản đồ xuất hiện.
![img.png](https://github.com/BuiDuc15/MultiAgent/blob/master/img/img.png)
2. Bước chạy
- Chọn Start, các Robot bắt đầu di chuyển theo quy tắc bước di chuyển ở trên. 
![img_1.png](https://github.com/BuiDuc15/MultiAgent/blob/master/img/img_1.png)
- Chọn Next Step, các Robot tiếp tục di chuyển sao cho chúng về được vị trí bắt đầu của từng riêng con. 
![img_2.png](https://github.com/BuiDuc15/MultiAgent/blob/master/img/img_2.png)
![img_3.png](https://github.com/BuiDuc15/MultiAgent/blob/master/img/img_3.png)
3. Kết thúc quá trình chạy
- Các Robot sau khi về vị trí bắt đầu của từng riêng con sẽ kiểm tra tại ví trí đó không còn hướng di chuyển nào khả
thi thì quá trình quét dọn kết thúc. Tại lúc đó tất cả các ô trong phòng đều được các tác tử đi qua hết.
![img_4.png](https://github.com/BuiDuc15/MultiAgent/blob/master/img/img_4.png)
- Chương trih báo quét dọn thành công.
![img_5.png](https://github.com/BuiDuc15/MultiAgent/blob/master/img/img_5.png)

## Các vấn đề gặp phải và giải pháp
1. Các Robot liệu trong quá trình di chuyển liệu đâm vào nhau.
- Sẽ không thể nào đâm vào nhau. Vì chương trình xử lý bằng cách mỗi lần click Next Step, chương trình sẽ cho duyệt
từng Robot trong danh sách ***lần lượt*** chạy, tức là con Robot 1 chạy 1 bước, ghi trạng thái ô vừa chạy vào trong
square tương ứng là đã chạy qua. Rồi con Robot tiếp theo mới thực hiện chạy. 
- Và hơn thế nữa 2 con robot sẽ không trùng đường chạy của nhau, sẽ không đi đè vào nhau.
2. Phân chia quãng đường chạy của các Robot đều.
- Vì các Robot không thể biết trước được môi trường chạy của nó sẽ có kích thước thế nào, chỉ có thể
biết được bước tiếp theo của nó liệu có thể đi được không nhờ check vị trí tọa độ thay vì dùng sensor như thực tế.

## Định hướng phát triên
- Nhóm tác giả sẽ nghiên cứu và phát triển cho các bước di chuyển tối ưu hơn trong các phiên bản tiếp theo.