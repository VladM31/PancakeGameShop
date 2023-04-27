using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class LoginValidate : MonoBehaviour
{
    public Button confirmButton;
    public InputField phoneInput;
    public InputField passwordInput;
    public Text errorText;

    public void SubmitForm()
    {
        string phone = phoneInput.text;
        string password = passwordInput.text;

        if (phone == "admin" && password == "password")
        {
            SceneManager.LoadScene("Start Screen 1");
        }
        else
        {
            errorText.text = "Invalid phone number or password.";
        }
    }
}