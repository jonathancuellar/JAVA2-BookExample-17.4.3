// Fig 17.12 CreditInquiry.java
// This program reads a file sequentially and displays the
// contents based on the type of account the user requests
// Credit balance, debit balance pr zero balance
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.IllegalStateException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CreditInquiry
{
  private MenuOption accountType;
  private Scanner input;
  private final static MenuOption[] choices = {MenuOption.ZERO_BALANCE,
    MenuOption.CREDIT_BALANCE, MenuOption.DEBIT_BALANCE,
    MenuOption.END};

  // read records from file and display only records of appropraite type
  private void readRecords()
  {
    // object to store data that will be written to file
    AccountRecord record = new AccountRecord();

    try // read records
    {
      // open file to read from beginning
      input = new Scanner(new File("clients.txt"));

      while (input.hasNext() ) //input the values from the file
      {
        record.setAccount(input.nextInt() ); // read account number
        record.setFirstName(input.next() ); // read first name
        record.setLastName(input.next() ); // read last name
        record.setBalance(input.nextDouble() ) ; // read balance

        // if proper account type, display record
        if(shouldDisplay(record.getBalance() ) )
          System.out.printf("%-10d%-12s%-12s%10.2f\n",
            record.getAccount(), record.getFirstName(),
            record.getLastName(), record.getBalance() );
      } // end while

    } // end try
    catch (NoSuchElementException nsee)
    {
      System.err.println("File improperly formed");
      input.close();
      System.exit(1);
    } // end catch

    catch (IllegalStateException ise)
    {
      System.err.println("Error reading from file");
      System.exit(1);
    } // end catch

    catch (FileNotFoundException fnfe)
    {
      System.err.println("File cannot be found.");
      System.exit(1);
    } // end catch

    finally
    {
      if(input != null)
        input.close(); // close the scanner and the file
    } // end finally

  } // end readRecords

  // use record type to determine if record should be displayed
  private boolean shouldDisplay(double balance)
  {
    if ( (accountType == MenuOption.CREDIT_BALANCE)
      && (balance < 0) )
      return true;

    else if ( (accountType == MenuOption.DEBIT_BALANCE)
      && (balance > 0) )
      return true;

    else if ( ( accountType == MenuOption.ZERO_BALANCE)
      && (balance == 0))
      return true;

    return false;
  } // end shouldDisplay

  // obtain request from user
  private MenuOption getRequest()
  {
    Scanner textIn = new Scanner(System.in);
    int request = 1;

    //display request options
    System.out.printf("\n%s\n%s\n%s\n%s\n%s\n",
      "Enter request", "1 - List account with zero balances",
      "2 - List accounts with credit balances",
      "3 - List accounts with debit balances", " 4 - End of run");

    try // attempt to input menu choice
    {
      do // input user request
      {
        System.out.print(" \n? ");
        request = textIn.nextInt();
      } while ( ( request < 1) || ( request > 4) );
    } // end try
    catch (NoSuchElementException nsee)
    {
      System.err.println("Invalid input.");
      System.exit(1);
    } // end catch

    return choices [request - 1]; // return enum value for the option

  } // end getRequest


  public void processRequests()
  {
    // get user's request (e.g. zero, credit or debit balance)
    accountType = getRequest();

    while(accountType != MenuOption.END)
    {
      switch(accountType)
      {
        case ZERO_BALANCE:
          System.out.println("\nAccounts with zero balances:\n");
          break;
        case CREDIT_BALANCE:
          System.out.println("\nAccoutns with credit balances:\n");
          break;
        case DEBIT_BALANCE:
          System.out.println("\nAccounts with debit balances:\n");
          break;
      } // end switch

      readRecords();
      accountType = getRequest();
    } // end while

  } // end process Request

} // end class