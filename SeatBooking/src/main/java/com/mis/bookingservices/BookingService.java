package com.mis.bookingservices;

import com.mis.CustException.CustException;
import com.mis.EmailService.EmailSenderService;
import com.mis.bookingmodels.*;
import com.mis.bookingrepositories.BookingRepo;
import com.mis.bookingrepositories.BuildingRepo;
import com.mis.bookingrepositories.SeatRepo;
import com.mis.bookingrepositories.UserRepo;
import com.mis.customclasses.Custom;
import com.mis.customclasses.Location;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private final BookingRepo bookingRepo;
    private final BuildingRepo buildingRepo;
    private final SeatRepo seatRepo;
    private final EmailSenderService emailSenderService;
    private final UserRepo userRepo;
    private final UserService userService;
    public BookingService(BookingRepo bookingRepo, BuildingRepo buildingRepo, SeatRepo seatRepo, EmailSenderService emailSenderService, UserRepo userRepo, UserService userService){
        this.bookingRepo = bookingRepo;
        this.buildingRepo = buildingRepo;
        this.seatRepo = seatRepo;
        this.emailSenderService = emailSenderService;
        this.userRepo = userRepo;
        this.userService = userService;
    }

    public ResponseEntity<String> findByLocation(Location custom) {
        Optional<List<Building>> buildingLists = bookingRepo.findAvailableSeatsAtLocation(custom.getBuilding_location());
        StringBuilder buildings = new StringBuilder();
        List<Building>buildingList = new ArrayList<>();
        if(buildingLists.isPresent())buildingList = buildingLists.get();
        if(buildingList.isEmpty())return new ResponseEntity<>("No Buildings Available at given Location", HttpStatus.NOT_FOUND);
        for(Building b : buildingList){
            buildings.append(b.toString1()).append("\n");
            System.out.println(b);
        }
        return new ResponseEntity<>(buildings.toString(),HttpStatus.FOUND);
    }
//    public ResponseEntity<String> listAllSeatsForTime(Custom custom){
//        List<Booking>bookedSeats =
//    }

    public void bookseat(Custom custom) throws CustException {

        if(!userService.verifyUser(custom.getUser()))
        {
            throw new CustException("Invalid ID/Password");
        }
        Optional<User> user=userRepo.findById(custom.getUser().getUserId());
        User user1=user.get();
        Optional<Building>building=buildingRepo.findById(custom.getBuildingName());
        if(building.isEmpty())
        {
            throw  new CustException("Invalid Building Details");
        }
        List<Floor>floors=building.get().getFloorList();
        Floor tempfloor=new Floor();
        boolean b=false;
        for(Floor obj:floors)
        {
            if(obj.getFloorNo()==custom.getFloor().getFloorNo())
            {
                b=true;
                tempfloor.setRoomList(obj.getRoomList());
                break;
            }
        }
        if(!b)
        {
            throw new CustException("Invalid Floor no");
        }
        List<Room>roomList=tempfloor.getRoomList();
        b=false;
        Room temproom=new Room();
        for(Room obj:roomList)
        {
            if(obj.getRoomNo()==custom.getRoom().getRoomNo())
            {
                b=true;
                temproom.setSeatList(obj.getSeatList());
                break;
            }
        }
        if(!b)
        {
            throw new CustException("Invalid Room Details");
        }
        List<Seat>seats=temproom.getSeatList();
        b=false;
        for(Seat obj:seats)
        {
            if(obj.getSeatNo() == custom.getSeat().getSeatNo())
            {
                b=true;
                break;
            }
        }
        if(!b)
        {
            throw new CustException("Invalid seat no");
        }
        String date1=custom.getBooking().getStartDate();
        String date2=custom.getBooking().getEndDate();
        Integer seatId=seatRepo.findId(custom.getSeat().getSeatNo(),custom.getFloor().getFloorNo(),custom.getBuilding().getBuildingName(),custom.getRoom().getRoomNo());
        List<Booking>bookingList=bookingRepo.getBookinglist1(custom.getBuilding().getBuildingName(),date1,date2,seatId);
        if(bookingList.isEmpty()) {
            Booking b1 = new Booking(custom.getBooking().getStartDate(),custom.getBooking().getEndDate(),custom.getBooking().getStartTime(),custom.getBooking().getEndTime(),user1,custom.getBuilding().getBuildingName(),seatId);
            bookingRepo.save(b1);
        }
        for(Booking obj:bookingList)
        {
           if((obj.getStartDate().compareTo(custom.getBooking().getEndDate())<0)&&(obj.getStartTime().compareTo(custom.getBooking().getEndTime())<0)&&(obj.getEndTime().compareTo(custom.getBooking().getStartTime())>0)&&(obj.getEndDate().compareTo(custom.getBooking().getStartDate())>0))
           {
               throw new CustException("Seat already booked");
           }
           else
           {
               Booking b1 = new Booking(custom.getBooking().getStartDate(),custom.getBooking().getEndDate(),custom.getBooking().getStartTime(),custom.getBooking().getEndTime(),user1,custom.getBuilding().getBuildingName(),seatId);
               bookingRepo.save(b1);
               emailSenderService.sendSimpleEmail(user1.getUserEmail(), "Booking Details","This is to inform you that you have booked a seat with following details "+b1+"\n * Seat No : "+custom.getSeat().getSeatNo()+"\n * Floor No : "+custom.getFloor().getFloorNo()+"\n * Room No : "+custom.getRoom().getRoomNo()+"\n\n\n\n In case of any query reach out to our customer care service"+"\n Email : onlineseatbookingsystem@gmail.com");
               break;
           }
        }

    }
}
