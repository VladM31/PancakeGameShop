using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;


public class PlayerLife : MonoBehaviour
{
    [SerializeField] private AudioSource deathSoundEffect;

    GameObject[] safeZones;

    private Animator anim;
    private Rigidbody2D rb;

    private void Start()
    {
        anim= GetComponent<Animator>();
        rb= GetComponent<Rigidbody2D>();
        safeZones = GameObject.FindGameObjectsWithTag("SafeZone");
    }


    //private void OnCollisionEnter2D(Collision2D collision)
    //{
    //    if(collision.gameObject.CompareTag("Trap"))
    //    {
    //        Die();
    //    }
    //}

    private void OnCollisionEnter2D(Collision2D collision)
    {
        if (collision.gameObject.tag == "Trap")
        {
            Health.health -= 1;

            if (Health.health == 0)
                Die();
            else
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

        }
    }

    private void Die()
    {
        deathSoundEffect.Play();
        rb.bodyType = RigidbodyType2D.Static;
        anim.SetTrigger("death");
    }

    private void RestartLevel()
    {
        SceneManager.LoadScene(SceneManager.GetActiveScene().name);
    }
}
