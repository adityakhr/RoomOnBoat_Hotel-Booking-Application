# RoomOnBoat A Hotel Booking Application

Hello,<br>
Welcome to the Online Hotel Booking Application System, I named it as "RoomOnBoat". You can find logo below of my Application.<br>
<p align="center">
	<be>
		<img src="Logo/RoomOnBoatLogo.png" alt="Logo" style="width:50%" >
</p>
Here in this project you can find some entity classes which are as follows..<br>
1. Host<br>
2. Users<br>
3. Booking<br>
4. Property<br>
5. Room<br>
<br>
As the names are indicating they are entity classes for generating the various tables and their relations, you can have a look and find the relations between them in below diagram.<br>

<p align="center">
	<br>
		<img src=Logo/Untitled.png style="width:100%" >
</p>

<br><br>
I have give three sides in this applications...<br><br>
Admin Functionality.....
  <br>
  <br>
  for using admin functionality you have to login as Admin.<br>
  Admin can do....
  <br>
    1 -> Add admin Only from user or Host (end points=>'http://localhost:8080/host/add_admin/host/{hostId}' or 'http://localhost:8080/host/add_admin/host/{userId}')<br>
	2 -> Delete user (end points=>'http://localhost:8080/host/delete_host/{userId}')<br>
	3 -> Delete host (end points=>'http://localhost:8080/host/delete_host/{hostId}')<br>
	4 -> Show users (end points=>'http://localhost:8080/host/get_users')<br>
	5 -> Show hosts (end points=>'http://localhost:8080/host/get_hosts')<br>
	6 -> Show Properties(end points=>'http://localhost:8080/host/get_properties')<br>
	7 -> Show Rooms(end points=>'http://localhost:8080/host/get_rooms')<br>
	8 -> Delete Property(end points=>'http://localhost:8080/host/delete_property/{propertyId}')<br>
	0 -> Delete Room(end points=>'http://localhost:8080/host/delete_room/{roomId})<br>
<br>
<br>
Customer Functionality.....
<br>
<br>
  for using Customer Functionality you have to login as Customer(if you have valid credential or you can Sign Up first and then Log In).
  <br>
  Customer can do...
  <br>
    1 -> List of restaurants & their food items<br>
	2 -> Order food<br>
	3 -> Checkout<br>
	4 -> Update details<br>
	0 -> Log out<br>
<br>
<br>
These were the some functionality of my application.
<br>
Thankyou.....
