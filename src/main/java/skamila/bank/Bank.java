package skamila.bank;

import skamila.bank.action.*;
import skamila.bank.database.CustomerAccountDatabase;
import skamila.cmdMenuFramework.Menu;
import skamila.cmdMenuFramework.input.ConsoleInput;
import skamila.cmdMenuFramework.menuEntry.MenuEntry;
import skamila.cmdMenuFramework.menuEntry.MenuEntryAction;
import skamila.cmdMenuFramework.menuEntry.MenuEntrySubmenu;
import skamila.cmdMenuFramework.menuPrinter.MenuPrinterStandard;
import skamila.cmdMenuFramework.menuView.StandardDataExtractor;
import skamila.cmdMenuFramework.menuView.StandardMenuView;

import java.io.IOException;
import java.util.ArrayList;

public class Bank {

    public static void main( String[] args ) {

        System.out.println("SYSTEM BANKOWY");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
        CustomerAccountDatabase database = new CustomerAccountDatabase("customerDatabase.txt");

        try {
            database.load();
        } catch (IOException e) {
            System.out.println("Błąd! Nie można załadować lub bazy danych. Program zostanie zamknięty.");
            System.exit(1);
        }

        Menu menu = makeMenu(database);
        menu.doMenu();


    }

    private static Menu makeMenu(CustomerAccountDatabase database){

        ArrayList <MenuEntry> menuList = new ArrayList<>();
        ArrayList <MenuEntry> submenuList = new ArrayList<>();

        submenuList.add(new MenuEntryAction(1,1,"Numer użytkownika", new DisplayClientsByIdAction(database)));
        submenuList.add(new MenuEntryAction(2,2,"Imię", new DisplayClientsByFirstNameAction(database)));
        submenuList.add(new MenuEntryAction(3,3,"Nazwisko", new DisplayClientsBySurnameAction(database)));
        submenuList.add(new MenuEntryAction(4,4,"PESEL", new DisplayClientsByPersonalIdentityNumberAction(database)));
        submenuList.add(new MenuEntryAction(5,5,"Miasto", new DisplayClientsByCityAction(database)));
        submenuList.add(new MenuEntryAction(6,6,"Kod pocztowy", new DisplayClientsByPostCodeAction(database)));
        submenuList.add(new MenuEntryAction(7,7,"Adres (ulica i numer)", new DisplayClientsByAddressAction(database)));
        submenuList.add(new MenuEntryAction(8,8,"Powrót do menu", new ExitAction()));

        menuList.add(new MenuEntryAction(1,1,"Wpłata", new PaymentAction(database)));
        menuList.add(new MenuEntryAction(2,2,"Wypłata", new WithdrawAction(database)));
        menuList.add(new MenuEntryAction(3,3,"Przelew", new TransferAction(database)));
        menuList.add(new MenuEntryAction(4,4,"Dodaj klienta", new AddClientAction(database)));
        menuList.add(new MenuEntryAction(5,5,"Usuń klienta", new DeleteClientAction(database)));
        menuList.add(new MenuEntrySubmenu(6,6,"Wyświetl klientów według kryterium", submenuList));
        menuList.add(new MenuEntryAction(7,7,"Wyświetl listę klientów", new DisplayAllClientsAction(database)));
        menuList.add(new MenuEntryAction(8,8,"Zamknij program", new ExitAction()));

        return new Menu(
                menuList,
                new StandardMenuView(),
                new StandardDataExtractor(),
                new MenuPrinterStandard(),
                new ConsoleInput()
        );

    }

}
