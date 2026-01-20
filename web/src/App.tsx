import { useEffect, useMemo, useState } from 'react'
import './App.css'
import {
  fetchState,
  rollForPlayer,
  startNewGame,
  type GameState,
} from './api/gameClient'
import HeroSection from './components/HeroSection'
import InsightPanel from './components/InsightPanel'
import PlayersPanel from './components/PlayersPanel'
import StatePanel from './components/StatePanel'

function App() {
  const [state, setState] = useState<GameState | null>(null)
  const [playersInput, setPlayersInput] = useState(2)
  const [loading, setLoading] = useState(false)
  const [actionLabel, setActionLabel] = useState<string | null>(null)
  const [error, setError] = useState<string | null>(null)

  const sortedPlayers = useMemo(() => {
    if (!state) {
      return []
    }
    return Object.values(state.players).sort((a, b) => a.id - b.id)
  }, [state])

  const currentPlayer =
    state && state.currentPlayerId != null
      ? state.players[String(state.currentPlayerId)]
      : null

  const loadState = async () => {
    setLoading(true)
    setActionLabel('Refreshing')
    try {
      setState(await fetchState())
      setError(null)
    } catch (err) {
      setError(err instanceof Error ? err.message : 'Failed to load game state.')
    } finally {
      setLoading(false)
      setActionLabel(null)
    }
  }

  const handleStartNewGame = async () => {
    setLoading(true)
    setActionLabel('Starting new game')
    try {
      setState(await startNewGame(playersInput))
      setError(null)
    } catch (err) {
      setError(err instanceof Error ? err.message : 'Failed to start game.')
    } finally {
      setLoading(false)
      setActionLabel(null)
    }
  }

  const handleRollForCurrentPlayer = async () => {
    if (!state || state.currentPlayerId == null) {
      return
    }
    setLoading(true)
    setActionLabel(`Rolling for Player ${state.currentPlayerId}`)
    try {
      setState(await rollForPlayer(state.currentPlayerId))
      setError(null)
    } catch (err) {
      setError(err instanceof Error ? err.message : 'Failed to roll.')
    } finally {
      setLoading(false)
      setActionLabel(null)
    }
  }

  useEffect(() => {
    loadState()
  }, [])

  return (
    <div className="app">
      <HeroSection
        state={state}
        playersInput={playersInput}
        onPlayersInputChange={setPlayersInput}
        onStartNewGame={handleStartNewGame}
        onRoll={handleRollForCurrentPlayer}
        onRefresh={loadState}
        loading={loading}
        actionLabel={actionLabel}
        error={error}
        currentPlayer={currentPlayer}
      />
      <main className="main-grid">
        <StatePanel state={state} currentPlayer={currentPlayer} />
        <PlayersPanel state={state} players={sortedPlayers} />
        <InsightPanel state={state} currentPlayer={currentPlayer} />
      </main>
    </div>
  )
}

export default App
