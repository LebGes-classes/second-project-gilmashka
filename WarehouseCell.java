public class WarehouseCell {
    String ID;
    Product product;
    int quantity;
    private static int quantityOfCells = 0;

    public WarehouseCell(Product product, int quantity) {
        this.ID = "A-" + (++quantityOfCells);
        this.product = product;
        this.quantity = quantity;
    }
}

