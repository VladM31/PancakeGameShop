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

    string apiUrl = "http://localhost:8005/api/v1/auth/login"; // URL API �����
    string levelIdsUrl = "http://localhost:8010/api/v1/bought-content/ids?gameIds=1";

    LoginResult loginResult = null;

    public void SubmitForm()
    {
        string phone = phoneInput.text;
        string password = passwordInput.text;

        StartCoroutine(PostUserData(phone, password));
    }
    IEnumerator GetWebData(string url)
    {
        
        using (UnityWebRequest webRequest = UnityWebRequest.Get(url))
        {
            string token = GlobalState.loginResult.tokenValue;
            
            webRequest.SetRequestHeader("Authorization", "Bearer " + token);
            yield return webRequest.SendWebRequest();

            if (webRequest.result != UnityWebRequest.Result.Success)
            {
                Debug.LogError(webRequest.error);
            }
            else
            {
                // Отримання даних з веб-запиту
                string responseText = webRequest.downloadHandler.text;
                var responce = JsonUtility.FromJson<ResponseResult>(responseText);

               
                GlobalState.loginResult.user.purchasedLevels = responce.content[0].levelIds;

                SceneManager.LoadScene("Start Screen 1");
            }
        }
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
            //Debug.Log("Status Code: " + request.responseCode);

            if (request.result == UnityWebRequest.Result.ConnectionError || request.result == UnityWebRequest.Result.ProtocolError)
            {
                // � ������ ������ ��������� ������� ������� ��������� �� ������
                Debug.Log("Error: " + request.error);
                errorText.text = request.downloadHandler.text;
            }
            else
            {
                // ������������ ���������� ������ � ������� JSON
                string jsonUserData = request.downloadHandler.text;
                this.loginResult = JsonUtility.FromJson<LoginResult>(jsonUserData);
                GlobalState.loginResult = loginResult;////////////////////////////////////////////////
                StartCoroutine(
                                //Debug.Log(loginResult.user.phoneNumber);
                                GetWebData(levelIdsUrl));
                

            }
        }

    }
}

// ������ ��� �������� ������ ������������ � ������� JSON
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
[Serializable]
public class ResponseResult
{
    public Game[] content;
}
[Serializable]
public class Game
{
    public long gameId;
    public long[] levelIds;
}