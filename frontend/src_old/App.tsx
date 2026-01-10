import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
//import './App.css'

function App(): JSX.Element {
  const [count, setCount] = useState<number>(0)

  const handleClick = (): void => {
    setCount((prevCount: number) => prevCount + 1)
  }

  return (
    <>
      <div  className="min-h-screen bg-gray-900 text-white
            flex flex-col justify-center items-center">
                
          <a href="https://vite.dev" target="_blank">
            <img src={viteLogo} 

            className="h-24 p-4"

            alt="Vite logo" 
            />
          </a>

          <a href="https://react.dev" target="_blank">
            <img src={reactLogo}
            
            className="h-32 transition duration-300 
            hover:scale-110 hover:drop-shadow-xl animate-spin-slow"

            alt="React logo"
            />
          </a>

        <h1>Vite + React</h1>

        <div className="p-8 text-center">
          
          <button onClick={handleClick}
            className="px-6 py-3 rounded-lg bg-gray-600 text-white font-bold shadow-md hover:bg-gray-700 transition"
          >
            count is {count}
          </button>

          <p className="mt-6 text-gray-600">
            Edit <code>src/App.tsx</code> and save to test HMR
          </p>
        </div>

        <div className="bg-gray-100 flex items-center justify-center">
        <h1 className="text-4xl font-bold text-blue-600">
          Tailwind
        </h1>
      </div>

        <p className="text-gray-400 mt-4 text-sm">

          Click on the Vite and React logos to learn more
        </p>
      </div>
    </>
  )
}

export default App
