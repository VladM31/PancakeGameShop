using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraController : MonoBehaviour
{
    public Transform target; // ������ �� ������
    public float minX; // ����������� ������� ������ �� ��� X
    public float maxX; // ������������ ������� ������ �� ��� X
    public float minY; // ����������� ������� ������ �� ��� Y
    public float maxY; // ������������ ������� ������ �� ��� Y

    private void LateUpdate()
    {
        // ���������, ���� �� ������ �� ������
        if (target == null)
            return;

        // �������� ������� ������� ������
        Vector3 targetPosition = target.position;

        // ������������ ������� ������ � �������� �������� ������
        float clampedX = Mathf.Clamp(targetPosition.x, minX, maxX);
        float clampedY = Mathf.Clamp(targetPosition.y, minY, maxY);

        // ������������� ����� ������� ������
        transform.position = new Vector3(clampedX, clampedY, transform.position.z);
    }
}
