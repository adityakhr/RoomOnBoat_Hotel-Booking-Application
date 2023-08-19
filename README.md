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
Database Name..<br>
RoomOnBoat
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
  For using admin functionality you have to login as Admin.<br>
  Admin can do....
  <br>
    1 -> Add admin Only from user or Host (end points=>'http://localhost:8080/admin/add_admin/host/{hostId}' or 'http://localhost:8080/admin/add_admin/host/{userId}')<br>
	2 -> Delete user (end point=>'http://localhost:8080/admin/delete_host/{userId}')<br>
	3 -> Delete host (end point=>'http://localhost:8080/admin/delete_host/{hostId}')<br>
	4 -> Show users (end point=>'http://localhost:8080/admin/get_users')<br>
	5 -> Show hosts (end point=>'http://localhost:8080/admin/get_hosts')<br>
	6 -> Show Properties(end point=>'http://localhost:8080/admin/get_properties')<br>
	7 -> Show Rooms(end point=>'http://localhost:8080/admin/get_rooms')<br>
	8 -> Delete Property(end point=>'http://localhost:8080/admin/delete_property/{propertyId}')<br>
	9 -> Delete Room(end point=>'http://localhost:8080/admin/delete_room/{roomId})<br>
 	10-> Show admins(end point=>'http://localhost:8080/admin/get_admins)
<br>
<br>
Host Functionality.....
<br>
<br>
  For using Host Functionality you have to login as Host.
  <br>
  Host can do...
  <br>
       1 -> Add admin Only from user or Host (end points=>'http://localhost:8080/host/add_admin/host/{hostId}' or 'http://localhost:8080/host/add_admin/host/{userId}')<br>
	2 -> Delete user (end point=>'http://localhost:8080/host/delete_host/{userId}')<br>
	3 -> Delete host (end point=>'http://localhost:8080/host/delete_host/{hostId}')<br>
	4 -> Show users (end point=>'http://localhost:8080/host/get_users')<br>
	5 -> Show hosts (end point=>'http://localhost:8080/host/get_hosts')<br>
	6 -> Show Properties(end point=>'http://localhost:8080/host/get_properties')<br>
	7 -> Show Rooms(end point=>'http://localhost:8080/host/get_rooms')<br>
	8 -> Delete Property(end point=>'http://localhost:8080/host/delete_property/{propertyId}')<br>
	9 -> Delete Room(end point=>'http://localhost:8080/host/delete_room/{roomId})<br>
 	10-> Show admins(end point=>'http://localhost:8080/host/get_admins)
<br>
<br>
These were the some functionality of my application.
<br>
Thankyou.....
