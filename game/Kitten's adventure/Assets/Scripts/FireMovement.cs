using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class FireMovement : MonoBehaviour
{
    [SerializeField] private GameObject[] waypoints;
    private int currentWaypointIndex = 0;
    [SerializeField] private float speed = 2f;

    private Animator anim;

    public bool isTalking;

    public bool isMoving;
    private enum MovementState { idle, flying }

    private void Start()
    {
        anim = GetComponent<Animator>();
        isTalking = true;
    }

    private void Update()
    {
        UpdateAnimationState();
        Moving();
    }

    private void UpdateAnimationState()
    {
        MovementState state;
        if (isMoving)
        {
            state = MovementState.flying;
        }
        else
        {
            state = MovementState.idle;
        }
        anim.SetInteger("state", (int) state);

    }

    

    private void Moving()
    {
        if (currentWaypointIndex < waypoints.Length && !isTalking)
        {
            isMoving= true;
            transform.position = Vector2.MoveTowards(transform.position, waypoints[currentWaypointIndex].transform.position, Time.deltaTime * speed);

            if (Vector2.Distance(transform.position, waypoints[currentWaypointIndex].transform.position) < .1f)
            {
                currentWaypointIndex++;

                if (currentWaypointIndex >= waypoints.Length)
                {
                    // ƒостигнута последн€€ точка, остановка перемещени€
                    currentWaypointIndex = waypoints.Length - 1;
                }
            }
        }
        else
        {
            isMoving = false;
        }
    }
}
