using System;
using System.Collections;
using System.Collections.Generic;
using System.Net;
using System.Text;
using UnityEditor.PackageManager.Requests;
using UnityEngine;
using UnityEngine.Networking;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class LoginValidate : MonoBehaviour
{
    public Button confirmButton;
    public InputField phoneInput;
    public InputField passwordInput;
    public Text errorText;

    string apiUrl = "http://localhost:8005/api/v1/auth/login"; // URL API сайта

    LoginResult loginResult = null;

    public void SubmitForm()
    {
        string phone = phoneInput.text;
        string password = passwordInput.text;

        StartCoroutine(PostUserData(phone, password));
    }

    IEnumerator PostUserData(string phoneNumber, string password)
    {
        string data = "{\"phoneNumber\":\"" + phoneNumber + "\",\"password\":\"" + password + "\"}";

        using (var request = new UnityWebRequest(apiUrl, "POST"))
        {
            byte[] bodyRaw = Encoding.UTF8.GetBytes(data);
            request.uploadHandler = (UploadHandler)new UploadHandlerRaw(bodyRaw);
            request.downloadHandler = (DownloadHandler)new DownloadHandlerBuffer();
            request.SetRequestHeader("Content-Type", "application/json");
            yield return request.SendWebRequest();
            Debug.Log("Status Code: " + request.responseCode);

            if (request.result == UnityWebRequest.Result.ConnectionError || request.result == UnityWebRequest.Result.ProtocolError)
            {
                // В случае ошибки обработки запроса выводим сообщение об ошибке
                Debug.Log("Error: " + request.error);
                errorText.text = request.downloadHandler.text;
            }
            else
            {
                // Обрабатываем полученные данные в формате JSON
                string jsonUserData = request.downloadHandler.text;
                this.loginResult = JsonUtility.FromJson<LoginResult>(jsonUserData);
                GlobalState.loginResult = loginResult;////////////////////////////////////////////////
                Debug.Log(loginResult.user.phoneNumber);
                SceneManager.LoadScene("Start Screen 1");

            }
        }

    }
}

// Классы для хранения данных пользователя в формате JSON
[Serializable]
public class User
{
    public int id;
    public string phoneNumber;
    public long[]? purchasedLevels;
}

[Serializable]
public class LoginResult
{
    public User user;
    public string tokenValue;
    public long tokenExpirationTime;
}
