using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;


public class PlayerLife : MonoBehaviour
{
    [SerializeField] private AudioSource deathSoundEffect;
    [SerializeField] private AudioSource damageSoundEffect;

    GameObject[] safeZones;

    private Animator anim;
    private Rigidbody2D rb;

    public static int health;
    [SerializeField] public GameObject Heart1, Heart2, Heart3;

    private void Start()
    {
        anim= GetComponent<Animator>();
        rb= GetComponent<Rigidbody2D>();
        safeZones = GameObject.FindGameObjectsWithTag("SafeZone");

        health = 3;
    }

    private void OnCollisionEnter2D(Collision2D collision)
    {

        if (collision.gameObject.tag == "Trap")
        {
            health -= 1;

            switch (health)
            {
                case 2:
                    Destroy(Heart1);
                    Hit();
                    break;

                case 1:
                    Destroy(Heart2);
                    Hit();
                    break;

                case 0:
                    Destroy(Heart3);
                    Die();
                    return;
            }

            //if (health == 0)
            //{
                
            //}
            //else
            //{
                TeleportToSafeZone();
            //}

        }
    }

 
    private void Die()
    {
        deathSoundEffect.Play();
        rb.bodyType = RigidbodyType2D.Static;
        anim.SetTrigger("death");
        Invoke("GetNextScene", 1.3f);
    }

    private void GetNextScene()
    {
        SceneManager.LoadScene("YouLost");

    }

    private void Hit()
    {
        damageSoundEffect.Play();        
    }

    private void TeleportToSafeZone()
    {
        GameObject nearestSafeZone = null;
        float nearestDistance = Mathf.Infinity;
        foreach (GameObject safeZone in safeZones)
        {
            float distance = Vector3.Distance(transform.position, safeZone.transform.position);
            if (distance < nearestDistance)
            {
                nearestDistance = distance;
                nearestSafeZone = safeZone;
            }
        }

        // перемещаем игрока в ближайшую безопасную зону
        transform.position = nearestSafeZone.transform.position;
    }
    //private void RestartLevel()
    //{
    //    SceneManager.LoadScene(SceneManager.GetActiveScene().name);
    //}
}
