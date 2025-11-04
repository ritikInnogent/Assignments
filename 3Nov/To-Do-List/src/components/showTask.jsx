import React from "react";

function showTask({ Task, onEdit, onDelete }) {
  return (
    <ul style={{ listStyleType: "none" }}>
      {Task.map((task, index) => (
        <li className="list" key={index}>
          {index + 1} - {task}&nbsp;&nbsp;
          <button className="edit" onClick={() => onEdit(index)}>Edit</button>&nbsp;&nbsp;
          <button className="delete" onClick={() => onDelete(index)}>Delete</button>
        </li>
      ))}
    </ul>
  );
}

export default showTask;
