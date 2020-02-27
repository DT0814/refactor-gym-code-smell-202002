package cc.xpbootcamp.code_smell_kit.$10_data_clumps;

public class Reservation {
    // Code smell: Data clumps
    public void reservateHotel(
            String numberOfRooms, String passportNumber, String passportIssueDate,
            String passportIssueOffice, String passportExpirationDate) {
        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.reserve(numberOfRooms, passportNumber, passportIssueDate,
                passportIssueOffice, passportExpirationDate);
    }

    public void reservateInternalFlight(
            String passengerName, String passportNumber, String passportIssueDate,
            String passportIssueOffice, String passportExpirationDate) {
        InternalFlightReservation internalFlightReservation = new InternalFlightReservation();
        internalFlightReservation.reserve(passengerName, passportNumber, passportIssueDate,
                passportIssueOffice, passportExpirationDate);
    }

    public void reservateBoat(
            String sailingLicenseNumber, String passportNumber, String passportIssueDate,
            String passportIssueOffice, String passportExpirationDate) {
        BoatReservation boatReservation = new BoatReservation();
        boatReservation.reserve(sailingLicenseNumber, passportNumber, passportIssueDate,
                passportIssueOffice, passportExpirationDate);
    }
}
