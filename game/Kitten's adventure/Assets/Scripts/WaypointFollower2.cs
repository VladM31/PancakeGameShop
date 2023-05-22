using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class WaypointFollower2 : MonoBehaviour
{
    [SerializeField] private GameObject[] waypoints;
    private int currentWaypointIndex = 0;
    [SerializeField] private float speed = 2f;

    private void Update()
    {
        if (currentWaypointIndex < waypoints.Length)
        {
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
    }

}
