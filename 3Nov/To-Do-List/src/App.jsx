import { useState, useEffect } from "react";
import "./components/my.css"
import AddTask from "./components/addTask";
import ShowTask from "./components/showTask";


function App() {
  const [Task, setTask] = useState(() => {
    const data = localStorage.getItem("tasks");
    return data ? JSON.parse(data) : ["Coding"];
  });
  const [newTask, setNewTask] = useState("");
  const [editIndex, setEditIndex] = useState(null);

  useEffect(() => {
    localStorage.setItem("tasks", JSON.stringify(Task));
  }, [Task]);

  let handleAddTask = () => {
    if (editIndex !== null) {
      let updatedTasks = [...Task];
      updatedTasks[editIndex] = newTask;
      setTask(updatedTasks);
      setEditIndex(null);
    } else {
      setTask((prevTask) => [...prevTask, newTask]);
    }
    setNewTask("");
  };

  let handleOnChange = (event) => {
    setNewTask(event.target.value);
  };

  let handleDelete = (index) => {
    let FilterArray = Task.filter((_, id) => id !== index);
    setTask(FilterArray);
  };

  let handleEdit = (index) => {
    setNewTask(Task[index]);
    setEditIndex(index);
  };

  return (
    <>
    <div className="app">
      <h1>To-Do-List</h1>
      <br /><br />
      <AddTask
        newTask={newTask}
        onChange={handleOnChange}
        onAdd={handleAddTask}
      />
      <hr />
      <h2>List of all tasks</h2>
      <hr />
      <ShowTask
        Task={Task}
        onEdit={handleEdit}
        onDelete={handleDelete}
      />
      </div>
    </>
  );
}

export default App;
