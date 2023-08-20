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
RoomOnBoat<br>
<br>
Database Software Name..<br>
RDBMS (MySQL)<br>
<br>
As the names are indicating they are entity classes for generating the various tables and their relations, you can have a look and find the relations between them in below diagram.<br>

<p align="center">
	<br>
		<img src=Logo/Untitled.png style="width:100%" >
</p>

<br><br>
I have give three sides in this applications...<br><br>
<h2 align="center">Admin Functionality.....</h2>
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
<h2 align="center">Host Functionality.....</h2>
<br>
<br>
  For using Host Functionality you have to login as Host.
  <br>
  Host can do...
  <br>
       1 -> Add property in his profile (end points=>'http://localhost:8080/host/{hostId}/properties')<br>
	2 -> Add room to his Property (end point=>'http://localhost:8080/host/{userId}/property/{propertyId}/rooms')<br>
	3 -> See his property (end point=>'http://localhost:8080/host/{hostId}/properties')<br>
	4 -> See his property's rooms (end point=>'http://localhost:8080/host/{hostId}/property/{propertyId}/rooms')<br>
	5 -> Update email (end point=>'http://localhost:8080/host/{hostId}/update_email')<br>
	6 -> Update password(end point=>'http://localhost:8080/host/{hostId}/update_password')<br>
	7 -> Update name(end point=>'http://localhost:8080/host/{hostId}/update_name')<br>
	8 -> Delete account(end point=>'http://localhost:8080/host/{hostId}/delete_account')<br>
	9 -> Update Booking Status(end point=>'http://localhost:8080/host/{hostId}/update_status/booking/{bookingId}')<br>
 	10-> Delete room(end point=>'http://localhost:8080/host/{hostId}/property/{propertyId}/delete_room/{roomId})<br>
        11-> Delete Property(end point=>'http://localhost:8080/host/{hostId}/delete_property/{propertyId}')<br>
<br>
<br>
<h2 align="center">User Functionality.....</h2>
<br>
<br>
  For using User Functionality you have to login as User.
  <br>
  User can do...
  <br>
       1 -> Show properties (end points=>'http://localhost:8080/user/properties')<br>
	2 -> Show room of Property (end point=>'http://localhost:8080/user/property/{propertyId}/get_rooms')<br>
	3 -> See his boking (end point=>'http://localhost:8080/user/{userId}/get_your_booking')<br>
	4 -> Update email (end point=>'http://localhost:8080/user/{userId}/update_email')<br>
	5 -> Update password(end point=>'http://localhost:8080/user/{userId}/update_password')<br>
	6 -> Update name(end point=>'http://localhost:8080/user/{userId}/update_name')<br>
	7 -> Delete account(end point=>'http://localhost:8080/user/{userId}/delete_account')<br>
	8 -> Book a room(end point=>'http://localhost:8080/user/{userId}/book_room/{roomId}')<br>
 	9-> Delete room from booking(end point=>'http://localhost:8080/user/{userId}/booking/{bookingId}/delete_booked_room/{roomId}')<br>
        10-> Confirm booking(end point=>'http://localhost:8080/user/{userId}/confirm_booking/{bookingId}')<br>
<br>
<p align="center">
	<h2>For all the getting request(Method="GET"), you can get it in pages and can order it(Default-> name/id). For pagination and sorting in Get method endpoints you have to add the thing which are as follows...</h2>
	<h4 align="center"> ?page={yourPageNumber}&count={ourItemCount}&order={desc/asc}</h4>
</p>
If you have Java 17 installed in your machine you can run this application in you machine for that here is the jar file below...<br>
<P align="center"><br>
	Get the .jar file <a href="Logo/">here</a><br>
</P>
<br>

<br>
These were the some functionality of my application.
<br>
Thankyou.....
