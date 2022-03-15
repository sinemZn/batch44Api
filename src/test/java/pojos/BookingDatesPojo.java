package pojos;

public class BookingDatesPojo {

//1) private degiskenleri olustur
    private String checkin;
    private String checkout;

 //2) getter ve setter olustur


    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public String getCheckout() {
        return checkout;
    }

    public String getCheckin() {
        return checkin;
    }

   //3) parametreli ve parametresiz constructor olustur

    public BookingDatesPojo(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }
    public BookingDatesPojo() {
    }
    //4)ToString
    @Override
    public String toString() {
        return "bookingdatesPojo{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }


}
