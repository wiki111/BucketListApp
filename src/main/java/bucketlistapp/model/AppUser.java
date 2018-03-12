package bucketlistapp.model;

public class AppUser{
  private long id;
  private String name;
  private String encryptedPassword;

  public User(){}

  public User(Long id, String name, String encryptedPassword){
    this.id = id;
    this.name = name;
    this.encryptedPassword = encryptedPassword;
  }

  public Long getId(){
    return id;
  }

  public void setId(Long id){
    this.id = id;
  }

  public String getName(){
    return name;
  }

  public void setName(String name){
    this.name = name;
  }

  public String getEncryptedPassword(){
    return encryptedPassword;
  }

  public void setEncryptedPassword(String encryptedPassword){
    this.encryptedPassword = encryptedPassword;
  }

  @Override
  public String toString(){
    return this.name + "/" + this.encryptedPassword;
  }
}
