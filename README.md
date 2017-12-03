# RoomBooking

App is implemented using MVP architecture.
Retrofit is used for network operations.
Dagger2 is used for dependency injection.

The app has following packages:

data: It contains all the data accessing and manipulating components.

di: Dependency providing classes using Dagger2.

ui: View classes along with their corresponding Presenters.

utils: Utility classes.

DataManger fetches the data from Ntework/DB based on the network connectivity.
Presenter has bussiness logic i.e . takes the data from DataManager and pass to the View.
View has only display logic.

<h3>Screenshots</h3>
<p>
  <img src="https://github.com/vinodkamble444/RoomBooking/blob/master/screenshots/Room%20List.png" width="300" height="550"/>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <img src="https://github.com/vinodkamble444/RoomBooking/blob/master/screenshots/Room%20detais.png" width="300" height="550"/>
</p>
<p>
  <img src="https://github.com/vinodkamble444/RoomBooking/blob/master/screenshots/timeslot.png" width="300" height="550"/>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <img src="https://github.com/vinodkamble444/RoomBooking/blob/master/screenshots/add%20attendees.png" width="300" height="550"/>
</p>
                         
                         
   

