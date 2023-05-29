using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CofferMovement : MonoBehaviour
{
    public bool isMoving = false;

    [SerializeField] private GameObject[] waypoints;

    private int currentWaypointIndex = 0;
    [SerializeField] private float speed = 2f;
    public bool isTouched = false;
    //private Animator anim;
    public Rigidbody2D rbPlayer;


    public bool firstPointIsGone = false;
    private enum MovementState { idle, flying }


    private void Start()
    {
        //anim = GetComponent<Animator>();
        //rb = GetComponent<Rigidbody2D>();
    }


    public void Update()
    {
        //UpdateAnimationState();
        Moving();
    }


    //private void UpdateAnimationState()
    //{
    //    MovementState state;
    //    if (rbPlayer.bodyType == RigidbodyType2D.Dynamic && currentWaypointIndex != waypoints.Length - 1)
    //    {
    //        state = MovementState.flying;
    //    }
    //    else
    //    {
    //        state = MovementState.idle;
    //    }
    //    anim.SetInteger("state", (int)state);

    //}



    private void Moving()
    {
        if (currentWaypointIndex < waypoints.Length && isMoving && !isTouched)
        {
            transform.position = Vector2.MoveTowards(transform.position, waypoints[currentWaypointIndex].transform.position, Time.deltaTime * speed);

            if (Vector2.Distance(transform.position, waypoints[currentWaypointIndex].transform.position) < .1f)
            {
                currentWaypointIndex++;

                if(currentWaypointIndex == 2)
                {
                    rbPlayer.bodyType = RigidbodyType2D.Dynamic;
                    firstPointIsGone = true;
                }

                if (currentWaypointIndex >= waypoints.Length)
                {
                    currentWaypointIndex = 0;
                }

                isMoving = true;
            }

        }

    }
}


