public abstract class Item {
    private String id;
    private String title;
    private int amount;

    public Item() {
        // Default constructor
    }

    public void setItemData(String id, String title, int amount) {
        this.id = id;
        this.title = title;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void getInfo() {
        System.out.println("ID: " + id + " | Title: " + title + " | Available: " + amount);
    }

    public void borrow() throws Exception {
        if (amount > 0) {
            amount--;
            System.out.println("You have borrowed: " + title);
        } else {
            throw new Exception("Item not available for borrowing.");
        }
    }

    public void returnItem() {
        amount++;
        System.out.println("You have returned: " + title);
    }
}
