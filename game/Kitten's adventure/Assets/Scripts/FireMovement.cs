
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class FireMovement : MonoBehaviour
{
    public Rigidbody2D rbPlayer;
    [SerializeField] private GameObject[] waypoints;
    private int currentWaypointIndex = 0;
    [SerializeField] private float speed = 2f;

    private Animator anim;
    private Rigidbody2D rb;
    
    private enum MovementState { idle, flying }

    private void Start()
    {
        anim = GetComponent<Animator>();
        rb = GetComponent<Rigidbody2D>();
    }

    private void Update()
    {
        UpdateAnimationState();
        Moving();
    }

    private void UpdateAnimationState()
    {
        MovementState state;
        if (rbPlayer.bodyType == RigidbodyType2D.Dynamic)
        {
            state = MovementState.flying;
        }
        else
        {
            state = MovementState.idle;
        }
        anim.SetInteger("state", (int)state);

    }



    private void Moving()
    {
        if (currentWaypointIndex < waypoints.Length && rbPlayer.bodyType == RigidbodyType2D.Dynamic)
        {
            transform.position = Vector2.MoveTowards(transform.position, waypoints[currentWaypointIndex].transform.position, Time.deltaTime * speed);

            if (Vector2.Distance(transform.position, waypoints[currentWaypointIndex].transform.position) < .1f)
            {
                currentWaypointIndex++;

                if (currentWaypointIndex >= waypoints.Length)
                {
                    currentWaypointIndex = waypoints.Length - 1;
                }
            }
        }
    }
}