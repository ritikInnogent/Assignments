import React from "react";

function addTask({ newTask, onChange, onAdd }) {
  return (
    <>
      <input
        type="text"
        placeholder="Enter your task"
        value={newTask}
        onChange={onChange}
      />&nbsp;&nbsp;
      <button onClick={onAdd}>Add-Task</button>
      <br /><br />
    </>
  );
}

export default addTask;
