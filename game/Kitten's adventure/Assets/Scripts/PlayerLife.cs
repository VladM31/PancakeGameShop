using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerLife : MonoBehaviour
{
    [SerializeField] public GameObject warning;
    [SerializeField] public GameObject[] timeLine;
    public Transform otherObject; // ������ �� ������ ������, � ������� ������������ ���������
    public float maxDistance = 5f;

    private int timeRemaining = 8; // ��������� ����� ��� �������
    private bool isCounting = false; // ����, �����������, ���� �� ������ �������
    private float timer = 0f; // ������ ��� ������������ ���������� �������

    private bool isAlreadyShown = false;
    private bool isDead = false;

    [SerializeField] private AudioSource deathSoundEffect;

    private Animator anim;
    private Rigidbody2D rb;



    private void Start()
    {
        anim = GetComponent<Animator>();
        rb = GetComponent<Rigidbody2D>();

        warning.SetActive(false);
        foreach (var obj in timeLine) 
        {
            obj.SetActive(false);
        }
    }
    void Update()
    {
        // �������� ��������� ����� ���������� � ������ ��������
        float distance = Vector2.Distance(transform.position, otherObject.position);

        if (distance > maxDistance && !isDead)
        {
            ShowTimeLine();
            isCounting = true;
            TimeCounting();
        }
        else
        {
            HideTimeLine();
            isAlreadyShown = false;
            isCounting = false;
            timeRemaining = 8;
            timer = 0f;
            isAlreadyShown = false;
            Debug.Log("����� �� �������������.");
        }
    }

    void Die()
    {
        deathSoundEffect.Play();
        rb.bodyType = RigidbodyType2D.Static;
        rb.constraints = RigidbodyConstraints2D.FreezePosition;
        rb.constraints |= RigidbodyConstraints2D.FreezeRotation;
        anim.SetTrigger("death");

        Debug.Log("You dead");
    }

    void ShowTimeLine()
    {
        warning.SetActive(true);

        if (!isAlreadyShown)
        {
            foreach (var obj in timeLine)
            {
                obj.SetActive(true);
            }
            isAlreadyShown = true;
        }
    }

    void HideTimeLine()
    {
        warning.SetActive(false);

        if (!isAlreadyShown)
        {
            foreach (var obj in timeLine)
            {
                obj.SetActive(false);
            }
            isAlreadyShown = true;
        }
    }




    void DecreaseTimeLine(int timeRemaining)
    {
        timeLine[timeRemaining].SetActive(false);
    }


    void TimeCounting()
    {
        if (isCounting)
        {
            timer += Time.deltaTime;

            if (timer >= 1f)
            {
                timer -= 1f;

                if (timeRemaining > 0)
                {
                    timeRemaining--;
                    DecreaseTimeLine(timeRemaining);
                    Debug.Log("�������� �������: " + timeRemaining + " ���.");
                    
                }

                if (timeRemaining <= 0)
                {
                    isDead = true;
                    Die();
                    isCounting = false;
                }
            }
        }
    }
}
