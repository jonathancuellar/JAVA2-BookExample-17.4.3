//Fig 17.4 AccountRecord.java

public class AccountRecord
{
  private int account;
  private String firstName;
  private String lastName;
  private double balance;

  // no-argument constructor
  public AccountRecord()
  {
    this(0, "", "", 0.0);
  }

  //initialize record
  public AccountRecord(int acct, String first, String last, double bal)
  {
    setAccount(acct);
    setFirstName(first);
    setLastName(last);
    setBalance(bal);
  }

  //setters
  public void setAccount(int acct)
  {
    account = acct;
  }

  public int getAccount()
  {
    return account;
  }

  public void setFirstName(String first)
  {
    firstName = first;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public void setLastName(String last)
  {
    lastName = last;
  }

  public String getLastName()
  {
    return lastName;
  }

  public void setBalance(double bal)
  {
    balance = bal;
  }

  public double getBalance()
  {
    return balance;
  }
}
