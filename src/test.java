public class test {
    public static void main(String[] args) {
        Buffer<String> buffer = new Deposit<>(1000);
        Miner miner = new Miner(buffer);
        TreasureRoom treasureRoom = new TreasureRoom();
        Guardsman guardsman = new Guardsman(treasureRoom);
        ValuableTransporter valuableTransporter = new ValuableTransporter(buffer,guardsman);
        ValuableTransporter valuableTransporter1 = new ValuableTransporter(buffer,guardsman);
        Thread t1 = new Thread(miner, "miner 1");
        Thread t2 = new Thread(miner, "miner 2");
        Thread t3 = new Thread(miner, "miner 3");

        Thread t4 = new Thread(valuableTransporter, "transporter 1");
        Thread t5 = new Thread(valuableTransporter1, "transporter 2");
        King king = new King(guardsman);
        Thread t6 = new Thread(king,"King");
        Accountant accountant = new Accountant(guardsman);
        Thread t7 = new Thread(accountant, "Accountant 1");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();

    }
}
